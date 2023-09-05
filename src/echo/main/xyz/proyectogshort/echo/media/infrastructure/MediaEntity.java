package xyz.proyectogshort.echo.media.infrastructure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "medias")
@Data
public final class MediaEntity {
    @Id
    @Column(name = "id")
    private String idValue;

    @Column(name = "media_order")
    private Long mediaOrder;
    @Column(name = "order_id")
    private String orderIdValue;
    @Column(name = "order_source")
    private String orderSource;
    @Column(name = "order_source_url")
    private String orderSourceUrlValue;
}
