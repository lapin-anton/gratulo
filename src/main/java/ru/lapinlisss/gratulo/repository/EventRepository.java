package ru.lapinlisss.gratulo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lapinlisss.gratulo.model.entity.Account;
import ru.lapinlisss.gratulo.model.entity.Event;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByAccount(Account account);

}
