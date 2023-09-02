package xyz.proyectogshort.echo.order.infrastructure.hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "orders")
@Data
public class OrderEntity {
    @Id
    @Column(name = "id")
    private String idValue;
    @Column(name = "source")
    private String source;
    @Column(name = "sourceUrl")
    private String sourceUrlValue;
    private long mediaCount;
    private String author;
}
