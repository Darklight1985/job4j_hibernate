package ru.job4j.hql;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "base_vac")
public class BaseVac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vacancy> vacancy = new ArrayList<>();

    public static BaseVac of(String name) {
        BaseVac baseVac = new BaseVac();
        baseVac.name = name;
        return baseVac;
    }

    public void addVac(Vacancy vacancy) {
        this.vacancy.add(vacancy);
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseVac baseVac = (BaseVac) o;
        return id == baseVac.id && Objects.equals(name, baseVac.name) && Objects.equals(vacancy, baseVac.vacancy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, vacancy);
    }

    @Override
    public String toString() {
        return "BaseVac{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", vacancy=" + vacancy
                + '}';
    }
}
