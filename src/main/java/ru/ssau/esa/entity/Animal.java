package ru.ssau.esa.entity;

import javax.persistence.*;

@Entity
@Table(name = "animal", schema = "public")
public class Animal extends NamedLongIdEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "type_id")
    private AnimalType animalType;

    @Column(name = "weight")
    private double weight;

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id.equals(animal.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
