package ru.job4j.lazy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "models")
public class ModelAvto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "mark_id")
    private MarkAvto markAvto;

    public static ModelAvto of(String name, MarkAvto markAvto) {
        ModelAvto modelAvto = new ModelAvto();
        modelAvto.name = name;
        modelAvto.markAvto = markAvto;
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

    public MarkAvto getMarkAvto() {
        return markAvto;
    }

    public void setMarkAvto(MarkAvto markAvto) {
        this.markAvto = markAvto;
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

    @Override
    public String toString() {
        return "ModelAvto{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", markAvto=" + markAvto
                + '}';
    }
}
