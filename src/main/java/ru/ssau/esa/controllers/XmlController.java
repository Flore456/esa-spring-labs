package ru.ssau.esa.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.ssau.esa.entity.Animal;
import ru.ssau.esa.entity.AnimalType;
import ru.ssau.esa.entity.Farmer;
import ru.ssau.esa.repos.AnimalRepo;
import ru.ssau.esa.repos.AnimalTypeRepo;
import ru.ssau.esa.repos.FarmerRepo;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

@Controller
@RequestMapping("/xml")
public class XmlController {

    private final AnimalRepo animalRepo;
    private final AnimalTypeRepo animalTypeRepo;
    private final FarmerRepo farmerRepo;

    @Autowired
    public XmlController(AnimalRepo animalRepo, AnimalTypeRepo animalTypeRepo, FarmerRepo farmerRepo) {
        this.animalRepo = animalRepo;
        this.animalTypeRepo = animalTypeRepo;
        this.farmerRepo = farmerRepo;
    }

    @ResponseBody
    @GetMapping(path = "/farmers", produces = MediaType.APPLICATION_XML_VALUE)
    private ModelAndView getFarmers() throws JsonProcessingException {
        Iterable<Farmer> list =  farmerRepo.findAll();
        return getModelAndView(list, "farmerXSL");
    }

    @ResponseBody
    @GetMapping(path = "/animals", produces = MediaType.APPLICATION_XML_VALUE)
    private ModelAndView getAnimals() throws JsonProcessingException{
        Iterable<Animal> list =  animalRepo.findAll();
        return getModelAndView(list, "animalXSL");
    }

    @ResponseBody
    @GetMapping(path = "/animalTypes", produces = MediaType.APPLICATION_XML_VALUE)
    private ModelAndView getAnimalTypes() throws JsonProcessingException{
        Iterable<AnimalType> list =  animalTypeRepo.findAll();
        return getModelAndView(list, "animalTypeXSL");
    }

    private ModelAndView getModelAndView(Iterable<?> list, String viewName) throws JsonProcessingException {
        String str = new XmlMapper().writeValueAsString(list);
        ModelAndView mod = new ModelAndView(viewName);
        Source src = new StreamSource(new StringReader(str));
        mod.addObject("ArrayList", src);
        return mod;
    }
}
