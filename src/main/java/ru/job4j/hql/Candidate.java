package ru.job4j.hql;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "candidate")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int experience;

    private int salary;

    @OneToOne(fetch = FetchType.LAZY)
    private BaseVac baseVac;

public static Candidate of(String name, int experience, int salary) {
    Candidate candidate = new Candidate();
    candidate.name = name;
    candidate.experience = experience;
    candidate.salary = salary;
    return candidate;
}

    public BaseVac getBaseVac() {
        return baseVac;
    }

    public void setBaseVac(BaseVac baseVac) {
        this.baseVac = baseVac;
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

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id && experience == candidate.experience
                && salary == candidate.salary
                && Objects.equals(name, candidate.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, experience, salary);
    }

    @Override
    public String toString() {
        return "Candidate{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", experience=" + experience
                + ", salary=" + salary
                + ", baseVac=" + baseVac
                + '}';
    }
}
