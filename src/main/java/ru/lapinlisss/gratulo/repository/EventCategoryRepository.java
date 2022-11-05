package ru.lapinlisss.gratulo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lapinlisss.gratulo.model.EventCategory;

@Repository
public interface EventCategoryRepository extends JpaRepository<EventCategory, Long> {
}
