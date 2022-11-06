package ru.lapinlisss.gratulo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {

    private Long id;

    private AccountDto account;

    private LocalDate eventDate;

    private String person;

    private EventCategoryDto eventCategory;

}
