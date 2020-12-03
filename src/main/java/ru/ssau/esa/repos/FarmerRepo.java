package ru.ssau.esa.repos;

import org.springframework.data.repository.CrudRepository;
import ru.ssau.esa.entity.Farmer;

public interface FarmerRepo extends CrudRepository<Farmer, Long> {

}
