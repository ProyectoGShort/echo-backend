package xyz.proyectogshort.echo.media.application.create;

import xyz.proyectogshort.echo.media.domain.Media;
import xyz.proyectogshort.echo.media.domain.MediaId;
import xyz.proyectogshort.echo.media.domain.MediaRepository;
import xyz.proyectogshort.echo.shared.domain.OrderId;
import xyz.proyectogshort.echo.shared.domain.OrderSource;
import xyz.proyectogshort.echo.shared.domain.OrderSourceUrl;
import xyz.proyectogshort.shared.domain.AggregateRoot;
import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.UuidGenerator;
import xyz.proyectogshort.shared.domain.bus.event.EventBus;

import java.util.Collection;
import java.util.List;
import java.util.stream.LongStream;

@Service
public final class MultiMediaCreator {

    private final UuidGenerator uuidGenerator;
    private final MediaRepository mediaRepository;
    private final EventBus eventBus;

    public MultiMediaCreator(UuidGenerator uuidGenerator, MediaRepository mediaRepository, EventBus eventBus) {
        this.uuidGenerator = uuidGenerator;
        this.mediaRepository = mediaRepository;
        this.eventBus = eventBus;
    }

    public void createMultiple(OrderId orderId, OrderSource orderSource, OrderSourceUrl orderSourceUrl, long orderMediaCount) {

        List<Media> mediaList = LongStream
                .rangeClosed(1, orderMediaCount)
                .mapToObj((i) -> createMedia(i, orderId, orderSource, orderSourceUrl))
                .toList();

        mediaList.forEach(mediaRepository::save);

        var events = mediaList
                .stream()
                .map(AggregateRoot::pullDomainEvents)
                .flatMap(Collection::stream)
                .toList();

        eventBus.publish(events);
    }

    private Media createMedia(long mediaOrder, OrderId orderId, OrderSource orderSource, OrderSourceUrl orderSourceUrl) {
        MediaId mediaId = new MediaId(uuidGenerator.generate());
        return Media.create(mediaId, mediaOrder, orderId, orderSource, orderSourceUrl);
    }
}
