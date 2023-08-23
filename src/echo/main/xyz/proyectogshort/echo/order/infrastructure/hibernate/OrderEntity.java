package xyz.proyectogshort.echo.order.infrastructure.hibernate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "orders")
public class OrderEntity {
    @Id
    private String id;

    public OrderEntity(String id) {
        this.id = id;
    }

    public OrderEntity() {

    }
}
