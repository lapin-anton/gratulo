package ru.lapinlisss.gratulo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String password;

    private String phone;

    @Column(name = "tg_tag")
    private String tgTag;

    @Column(name = "tg_group")
    private String tgGroup;

    @OneToMany(mappedBy = "account")
    private List<Event> events;

}
