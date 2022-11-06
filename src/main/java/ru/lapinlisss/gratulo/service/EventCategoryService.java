package ru.lapinlisss.gratulo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lapinlisss.gratulo.mapper.CustomMapper;
import ru.lapinlisss.gratulo.model.dto.EventCategoryDto;
import ru.lapinlisss.gratulo.model.entity.EventCategory;
import ru.lapinlisss.gratulo.repository.EventCategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventCategoryService {

    private final EventCategoryRepository eventCategoryRepository;

    public List<EventCategoryDto> findAll() {
        return eventCategoryRepository.findAll()
                .stream().map(CustomMapper.INSTANCE::mapEventCategoryToEventCategoryDto)
                .collect(Collectors.toList());
    }

    public EventCategory findById(Long id) {
        return eventCategoryRepository.findById(id).orElse(null);
    }
}
