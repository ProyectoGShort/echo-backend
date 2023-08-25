package xyz.proyectogshort.test.test.infrastructure.hibernate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "tests")
public class TestEntity {
    @Id
    private String id;

    public TestEntity(String id) {
        this.id = id;
    }

    public TestEntity() {

    }
}
