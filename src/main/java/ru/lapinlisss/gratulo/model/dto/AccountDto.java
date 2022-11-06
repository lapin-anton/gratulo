package ru.lapinlisss.gratulo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Long id;

    private String login;

    private String password;

    private String phone;

    private String tgTag;

    private String tgGroup;

}
