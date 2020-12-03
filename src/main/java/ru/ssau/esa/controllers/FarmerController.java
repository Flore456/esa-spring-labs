package ru.ssau.esa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ssau.esa.entity.Farmer;
import ru.ssau.esa.repos.FarmerRepo;
import ru.ssau.esa.response.BadResponse;
import ru.ssau.esa.response.GoodResponse;
import ru.ssau.esa.response.ServerResponse;

@RestController
public class FarmerController {

    private final FarmerRepo repo;

    @Autowired
    public FarmerController(FarmerRepo repo) {
        this.repo = repo;
    }

    @GetMapping(path = "/farmers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private Iterable<Farmer> findAll(){
        return repo.findAll();
    }

    @GetMapping(path = "/add/farmer", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private ServerResponse add(String name, String surname){
        Farmer farmer = new Farmer();
        farmer.setName(name);
        farmer.setSurname(surname);
        Farmer f = repo.save(farmer);
        return new GoodResponse(f);
    }

    @GetMapping(path = "/delete/farmer", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private ServerResponse delete(long id){
        Farmer farmer = repo.findById(id).orElse(null);
        if (farmer == null){
            return new BadResponse("Farmer not found");
        }
        if (!farmer.getAnimals().isEmpty()){
            return new BadResponse("Farmer has animals");
        }
        repo.delete(farmer);
        return new GoodResponse();
    }
}
