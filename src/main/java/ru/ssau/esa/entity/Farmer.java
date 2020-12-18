package ru.ssau.esa.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "farmer", schema = "public")
public class Farmer extends NamedLongIdEntity{

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "farmer")
    private List<Animal> animals;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Farmer farmer = (Farmer) o;
        return id.equals(farmer.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Farmer{").append("\n\t");
        sb.append("id=").append(id).append(",\n\t");
        sb.append("surname='").append(surname).append('\'').append(",\n\t");
        sb.append("name='").append(name).append('\'').append(",\n\t");
        sb.append("email='").append(email).append('\'').append("\n");
        sb.append('}');
        return sb.toString();
    }
}
