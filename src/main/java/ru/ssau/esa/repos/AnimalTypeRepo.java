package ru.ssau.esa.repos;

import org.springframework.data.repository.CrudRepository;
import ru.ssau.esa.entity.AnimalType;

public interface AnimalTypeRepo extends CrudRepository<AnimalType, Long> {

}
