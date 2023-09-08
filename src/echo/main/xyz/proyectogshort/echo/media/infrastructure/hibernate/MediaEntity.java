package xyz.proyectogshort.echo.media.infrastructure.hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.proyectogshort.echo.media.domain.Media;
import xyz.proyectogshort.echo.media.domain.MediaId;
import xyz.proyectogshort.echo.media.domain.MediaSourceUrl;
import xyz.proyectogshort.echo.media.domain.MediaStatus;
import xyz.proyectogshort.echo.shared.domain.OrderId;
import xyz.proyectogshort.echo.shared.domain.Source;
import xyz.proyectogshort.echo.shared.domain.OrderSourceUrl;

@Data
@NoArgsConstructor
@Entity(name = "medias")
public final class MediaEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "position")
    private Long position;
    @Column(name = "source")
    private String source;
    @Column(name = "order_id")
    private String orderId;
    @Column(name = "order_source_url")
    private String orderSourceUrl;
    @Column(name = "status")
    private String status;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "media_source_url")
    private String mediaSourceUrl;

    public MediaEntity(Media media) {
        this.id = media.getId().value();
        this.position = media.getPosition();
        this.source = media.getSource().name();
        this.orderId = media.getOrderId().value();
        this.orderSourceUrl = media.getOrderSourceUrl().value();
        this.status = media.getStatus().name();
        this.title = media.getTitle();
        this.author = media.getAuthor();
        this.mediaSourceUrl = media.getMediaSourceUrl() != null ? media.getMediaSourceUrl().value() : null;
    }

    public Media toMedia() {
        return new Media(
                new MediaId(id),
                position,
                Source.valueOf(source),
                new OrderId(orderId),
                new OrderSourceUrl(orderSourceUrl),
                MediaStatus.valueOf(status),
                title,
                author,
                mediaSourceUrl != null ? new MediaSourceUrl(mediaSourceUrl) : null
        );
    }
}
