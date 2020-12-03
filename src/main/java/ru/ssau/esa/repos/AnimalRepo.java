package ru.ssau.esa.repos;

import org.springframework.data.repository.CrudRepository;
import ru.ssau.esa.entity.Animal;

public interface AnimalRepo extends CrudRepository<Animal, Long> {

}
