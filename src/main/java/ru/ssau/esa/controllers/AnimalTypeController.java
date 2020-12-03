package ru.ssau.esa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ssau.esa.entity.AnimalType;
import ru.ssau.esa.repos.AnimalTypeRepo;
import ru.ssau.esa.response.BadResponse;
import ru.ssau.esa.response.GoodResponse;
import ru.ssau.esa.response.ServerResponse;

@RestController
public class AnimalTypeController {

    private final AnimalTypeRepo repo;

    @Autowired
    public AnimalTypeController(AnimalTypeRepo repo) {
        this.repo = repo;
    }

    @GetMapping(path = "/animalTypes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private Iterable<AnimalType> findAll(){
        return repo.findAll();
    }

    @GetMapping(path = "/add/animalType", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private ServerResponse add(String name){
        AnimalType animalType = new AnimalType();
        animalType.setName(name);
        AnimalType newType = repo.save(animalType);
        return new GoodResponse(newType);
    }

    @GetMapping(path = "/delete/animalType", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private ServerResponse delete(long id){
        AnimalType animalType = repo.findById(id).orElse(null);
        if (animalType == null){
            return new BadResponse("AnimalType not found");
        }
        if (!animalType.getAnimals().isEmpty()){
            return new BadResponse("This type includes at least one animal");
        }
        repo.delete(animalType);
        return new GoodResponse();
    }
}

