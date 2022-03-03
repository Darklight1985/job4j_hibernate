package ru.job4j.hiber.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "models")
public class ModelAvto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public static ModelAvto of(String name) {
        ModelAvto modelAvto = new ModelAvto();
        modelAvto.name = name;
        return modelAvto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ModelAvto modelAvto = (ModelAvto) o;
        return id == modelAvto.id && Objects.equals(name, modelAvto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
