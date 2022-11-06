package ru.lapinlisss.gratulo.util;

import ru.lapinlisss.gratulo.model.dto.EventDto;

public class MessageUtil {

    private MessageUtil() {}

    public static String generateMessage(EventDto eventDto) {
        return String.format("Привет! Сегодня %s отмечает %s. Поздравьте его!", eventDto.getPerson(), eventDto.getEventCategory().getName());
    }

}
