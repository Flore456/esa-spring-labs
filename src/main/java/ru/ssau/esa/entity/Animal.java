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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Animal{").append("\n\t");
        sb.append("id=").append(id).append(",\n\t");
        sb.append("farmer='").append(farmer.getName()).append(' ').append(farmer.getSurname()).append("',\n\t");
        sb.append("animalType='").append(animalType.getName()).append("',\n\t");
        sb.append("weight=").append(weight).append(",\n\t");
        sb.append("name='").append(name).append('\'').append("\n");
        sb.append('}');
        return sb.toString();
    }
}
