package ru.lapinlisss.gratulo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.lapinlisss.gratulo.model.dto.AccountDto;
import ru.lapinlisss.gratulo.model.dto.EventCategoryDto;
import ru.lapinlisss.gratulo.model.dto.EventDto;
import ru.lapinlisss.gratulo.model.entity.Account;
import ru.lapinlisss.gratulo.model.entity.Event;
import ru.lapinlisss.gratulo.model.entity.EventCategory;

@Mapper
public interface CustomMapper {

    CustomMapper INSTANCE = Mappers.getMapper(CustomMapper.class);

    AccountDto mapAccountToAccountDto(Account account);

    EventCategoryDto mapEventCategoryToEventCategoryDto(EventCategory eventCategory);

    EventDto mapEventToEventDto(Event event);

}