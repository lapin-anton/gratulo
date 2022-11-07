package ru.lapinlisss.gratulo.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.lapinlisss.gratulo.model.entity.Account;
import ru.lapinlisss.gratulo.repository.AccountRepository;

import java.util.Optional;
import java.util.regex.Pattern;

@Slf4j
@Service
public class TelegramBot extends TelegramLongPollingBot {

    private String token;

    private String botName;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    public TelegramBot(@Value("${telegram.bot.token}") String token, @Value("${telegram.bot.name}") String botName) {
        this.token = token;
        this.botName = botName;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()
                && update.getMessage().hasText()
                && isSubscribe(update.getMessage().getText())) {
            String chatId = String.valueOf(update.getMessage().getChatId());
            String text = update.getMessage().getText();
            String[] tokens = text.split("\\s");
            String login = tokens[1];
            String password = tokens[2];
            Optional<Account> accountDb = accountRepository.findAccountByLoginAndPassword(login, password);
            SendMessage message = new SendMessage();
            String response;
            if (accountDb.isPresent()) {
                Account account = accountDb.get();
                account.setTgId(chatId);
                accountRepository.saveAndFlush(account);
                response = "You are subscribed successfully:)";
            } else {
                response = "Something went wrong. Please, check your credentials.";
            }
            message.setChatId(chatId);
            message.setText(response);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String chatId, String text) {
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(chatId);
        message.enableHtml(true);
        try {
            execute(message);
            log.info("Message send successfully to chat {}", chatId);
            log.info(String.format("Message text: %s", message.getText()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private boolean isSubscribe(String message) {
        return Pattern.matches("^subscribe\\s[\\w]{3,128}\\s[\\w\\S]{3,128}$", message);
    }

}
