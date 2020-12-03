package ru.ssau.esa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "type", schema = "public")
public class AnimalType extends NamedLongIdEntity{

    @JsonIgnore
    @OneToMany(mappedBy = "animalType")
    private List<Animal> animals;

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalType type = (AnimalType) o;
        return id.equals(type.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
