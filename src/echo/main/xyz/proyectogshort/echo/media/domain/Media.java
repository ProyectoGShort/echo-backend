package xyz.proyectogshort.echo.media.domain;

import xyz.proyectogshort.echo.shared.domain.OrderId;
import xyz.proyectogshort.echo.shared.domain.Source;
import xyz.proyectogshort.echo.shared.domain.OrderSourceUrl;
import xyz.proyectogshort.shared.domain.AggregateRoot;

public final class Media extends AggregateRoot {

    private final MediaId id;
    private final long position;
    private final Source source;
    private final OrderId orderId;
    private final OrderSourceUrl orderSourceUrl;
    private MediaStatus status;
    private String title;
    private String author;
    private MediaSourceUrl mediaSourceUrl;

    public Media(
            MediaId id,
            long position,
            Source source,
            OrderId orderId,
            OrderSourceUrl orderSourceUrl,
            MediaStatus status,
            String title,
            String author,
            MediaSourceUrl mediaSourceUrl
    ) {
        this.id = id;
        this.position = position;
        this.source = source;
        this.orderId = orderId;
        this.orderSourceUrl = orderSourceUrl;
        this.status = status;
        this.title = title;
        this.author = author;
        this.mediaSourceUrl = mediaSourceUrl;
    }

    public static Media create(MediaId id, long position, Source source, OrderId orderId, OrderSourceUrl orderSourceUrl) {
        Media media = new Media(
                id,
                position,
                source,
                orderId,
                orderSourceUrl,
                MediaStatus.INITIALIZED,
                null,
                null,
                null
        );

        media.record(new MediaCreatedEvent(media.id.value(), orderId.value()));

        return media;
    }

    public MediaId getId() {
        return id;
    }

    public long getPosition() {
        return position;
    }

    public Source getSource() {
        return source;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public OrderSourceUrl getOrderSourceUrl() {
        return orderSourceUrl;
    }

    public MediaStatus getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public MediaSourceUrl getMediaSourceUrl() {
        return mediaSourceUrl;
    }

    public String getMediaSourceUrlValueOrNull() {
        return mediaSourceUrl != null ? mediaSourceUrl.value() : null;
    }

    public void updateWithMediaInfo(MediaInfo mediaInfo) {
        title = mediaInfo.title();
        author = mediaInfo.author();
        mediaSourceUrl = mediaInfo.mediaSourceUrl();

        record(new MediaDownloadStartedEvent(id.value(), orderId.value()));
    }

    public boolean matchesSource(Source orderSource) {
        if (this.source == null || orderSource == null) {
            return false;
        }

        return this.source.equals(orderSource);
    }

    public void markAsDownloaded() {
        status = MediaStatus.DOWNLOADED;
        record(new MediaDownloadedEvent(id.value()));
    }
}
