package ru.lapinlisss.gratulo.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "ev_date")
    private LocalDate eventDate;

    private String person;

    @ManyToOne
    @JoinColumn(name = "ev_category_id")
    private EventCategory eventCategory;

}
