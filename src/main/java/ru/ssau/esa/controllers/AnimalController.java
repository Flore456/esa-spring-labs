package ru.ssau.esa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ssau.esa.entity.Animal;
import ru.ssau.esa.entity.AnimalType;
import ru.ssau.esa.entity.Farmer;
import ru.ssau.esa.repos.AnimalRepo;
import ru.ssau.esa.repos.AnimalTypeRepo;
import ru.ssau.esa.repos.FarmerRepo;
import ru.ssau.esa.response.BadResponse;
import ru.ssau.esa.response.GoodResponse;
import ru.ssau.esa.response.ServerResponse;

@RestController
public class AnimalController {

    private final AnimalRepo repo;
    private final AnimalTypeRepo animalTypeRepo;
    private final FarmerRepo farmerRepo;

    @Autowired
    public AnimalController(AnimalRepo repo, AnimalTypeRepo animalTypeRepo, FarmerRepo farmerRepo) {
        this.repo = repo;
        this.animalTypeRepo = animalTypeRepo;
        this.farmerRepo = farmerRepo;
    }

    @GetMapping(path = "/animals", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private Iterable<Animal> findAll(){
        return repo.findAll();
    }

    @GetMapping(path = "/add/animal", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private ServerResponse add(String name, double weight, long farmerId, long animalTypeId){
        Animal animal = new Animal();
        animal.setName(name);
        animal.setWeight(weight);

        Farmer f = farmerRepo.findById(farmerId).orElse(null);
        if (f == null){
            return new BadResponse("Farmer not found");
        }
        animal.setFarmer(f);

        AnimalType t = animalTypeRepo.findById(animalTypeId).orElse(null);
        if (t == null){
            return new BadResponse("AnimalType not found");
        }
        animal.setAnimalType(t);

        Animal newAnimal = repo.save(animal);
        return new GoodResponse(newAnimal);
    }

    @GetMapping(path = "/delete/animal", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private ServerResponse delete(Long id){
        Animal animal = repo.findById(id).orElse(null);
        if (animal == null){
            return new BadResponse("Animal not found");
        }
        repo.delete(animal);
        return new GoodResponse();
    }
}

