package ru.job4j.lazy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "marks")
public class MarkAvto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "markAvto")
    private List<ModelAvto> models = new ArrayList<>();

    public static MarkAvto of(String name) {
        MarkAvto markAvto = new MarkAvto();
        markAvto.name = name;
        return markAvto;
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

    public List<ModelAvto> getModels() {
        return models;
    }

    public void setModels(List<ModelAvto> models) {
        this.models = models;
    }

    public void addModel(ModelAvto model) {
        this.models.add(model);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MarkAvto markAvto = (MarkAvto) o;
        return id == markAvto.id && Objects.equals(name, markAvto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "MarkAvto{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
