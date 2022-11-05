package ru.lapinlisss.gratulo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "event_category")
public class EventCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
