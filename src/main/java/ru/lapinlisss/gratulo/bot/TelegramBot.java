package ru.lapinlisss.gratulo.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Service
public class TelegramBot extends TelegramLongPollingBot {

    private String token;

    private String botName;

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
}
