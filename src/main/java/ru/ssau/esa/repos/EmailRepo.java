package ru.ssau.esa.repos;

import org.springframework.data.repository.CrudRepository;
import ru.ssau.esa.entity.Email;

public interface EmailRepo extends CrudRepository<Email, Long> {
}
