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
    private String title;
    @Column(name = "source")
    private String source;
    @Column(name = "source_url")
    private String sourceUrlValue;
    @Column(name = "media_count")
    private long mediaCount;
    private String author;
}
