package ru.job4j.integration;

import java.sql.Timestamp;
import java.util.Objects;

public class Orders {
    private int id;

    private String name;

    private String description;

    private Timestamp created;

    public Orders() {
    }

    public Orders(int id, String name, String description, Timestamp created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
    }

    public static Orders of(String name, String description) {
        Orders o = new Orders();
        o.name = name;
        o.description = description;
        o.created = new Timestamp(System.currentTimeMillis());
        return o;
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

    public String getDescription() {
        return description;
    }

    public Timestamp getCreated() {
        return created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Orders order = (Orders) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
