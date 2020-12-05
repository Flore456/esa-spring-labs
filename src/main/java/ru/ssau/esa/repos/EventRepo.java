package ru.ssau.esa.repos;

import org.springframework.data.repository.CrudRepository;
import ru.ssau.esa.entity.Event;

public interface EventRepo extends CrudRepository<Event, Long> {
}
