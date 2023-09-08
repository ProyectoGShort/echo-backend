package xyz.proyectogshort.echo.order.infrastructure.hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.proyectogshort.echo.order.domain.Order;
import xyz.proyectogshort.echo.shared.domain.OrderId;
import xyz.proyectogshort.echo.shared.domain.OrderSourceUrl;
import xyz.proyectogshort.echo.shared.domain.Source;

@Data
@NoArgsConstructor
@Entity(name = "orders")
public class OrderEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "title")
    private String title;
    @Column(name = "source")
    private String source;
    @Column(name = "source_url")
    private String sourceUrl;
    @Column(name = "media_count")
    private long mediaCount;
    @Column(name = "author")
    private String author;

    public OrderEntity(Order order) {
        this.id = order.getId().value();
        this.title = order.getTitle();
        this.source = order.getSource().name();
        this.sourceUrl = order.getSourceUrl().value();
        this.mediaCount = order.getMediaCount();
        this.author = order.getAuthor();
    }

    public Order toOrder() {
        return new Order(
                new OrderId(id),
                title,
                Source.valueOf(source),
                new OrderSourceUrl(sourceUrl),
                mediaCount,
                author
        );
    }
}
