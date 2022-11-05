package ru.lapinlisss.gratulo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lapinlisss.gratulo.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
