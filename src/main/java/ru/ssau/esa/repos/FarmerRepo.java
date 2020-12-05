package ru.ssau.esa.repos;

import org.springframework.data.repository.CrudRepository;
import ru.ssau.esa.entity.Farmer;

import java.util.List;

public interface FarmerRepo extends CrudRepository<Farmer, Long> {

    List<Farmer> findByEmail(String email);
}
