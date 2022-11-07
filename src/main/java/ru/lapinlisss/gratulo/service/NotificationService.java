package ru.lapinlisss.gratulo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.lapinlisss.gratulo.bot.TelegramBot;
import ru.lapinlisss.gratulo.mapper.CustomMapper;
import ru.lapinlisss.gratulo.model.dto.EventDto;
import ru.lapinlisss.gratulo.repository.EventRepository;
import ru.lapinlisss.gratulo.util.MessageUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final TelegramBot telegramBot;

    private final EventRepository eventRepository;

    @Scheduled(cron = "0 * * * * *")
    void execute() {
        List<EventDto> eventDtos = eventRepository.findAll().stream()
                .map(CustomMapper.INSTANCE::mapEventToEventDto)
                .collect(Collectors.toList());
        LocalDate current = LocalDate.now();
        for (EventDto ev: eventDtos) {
            if (ev.getEventDate() != null
                    && current.getMonth().equals(ev.getEventDate().getMonth())
                    && ev.getEventDate().getDayOfMonth() == current.getDayOfMonth()) {
                String text = MessageUtil.generateMessage(ev);
                telegramBot.sendMessage(ev.getAccount().getTgId(), text);
            }
        }
    }

}
