package ru.ssau.esa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ssau.esa.entity.EventType;
import ru.ssau.esa.entity.Farmer;
import ru.ssau.esa.notifications.JmsSenderService;
import ru.ssau.esa.repos.FarmerRepo;
import ru.ssau.esa.response.BadResponse;
import ru.ssau.esa.response.GoodResponse;
import ru.ssau.esa.response.ServerResponse;

@RestController
public class FarmerController {

    private final FarmerRepo repo;
    private final JmsSenderService jmsSenderService;

    @Autowired
    public FarmerController(FarmerRepo repo, JmsSenderService jmsSenderService) {
        this.repo = repo;
        this.jmsSenderService = jmsSenderService;
    }

    @GetMapping(path = "/farmers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private Iterable<Farmer> findAll(){
        return repo.findAll();
    }

    @GetMapping(path = "/add/farmer", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private ServerResponse add(String name, String surname, String email){
        if (!repo.findByEmail(email).isEmpty()){
            return new BadResponse("Duplicate email");
        }
        Farmer farmer = new Farmer();
        farmer.setName(name);
        farmer.setSurname(surname);
        farmer.setEmail(email);
        Farmer f = repo.save(farmer);
        jmsSenderService.sendEvent(Farmer.class, f, EventType.CREATE);
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
        jmsSenderService.sendEvent(Farmer.class, farmer, EventType.DELETE);
        return new GoodResponse();
    }
}
