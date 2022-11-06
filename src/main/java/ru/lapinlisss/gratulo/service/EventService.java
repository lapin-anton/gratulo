package ru.lapinlisss.gratulo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.lapinlisss.gratulo.mapper.CustomMapper;
import ru.lapinlisss.gratulo.model.dto.EventDto;
import ru.lapinlisss.gratulo.model.entity.Account;
import ru.lapinlisss.gratulo.model.entity.Event;
import ru.lapinlisss.gratulo.repository.EventRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public List<EventDto> findAllByAccount(Account account) {
        List<Event> eventDb = eventRepository.findAllByAccount(account);
        List<EventDto> rsl = eventDb.stream()
                .map(CustomMapper.INSTANCE::mapEventToEventDto)
                .collect(Collectors.toList());
        return rsl;
    }

    public void add(Event event) {
        eventRepository.saveAndFlush(event);
    }

    public void update(Event event) {
        eventRepository.saveAndFlush(event);
    }

    public Event findById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }
}
