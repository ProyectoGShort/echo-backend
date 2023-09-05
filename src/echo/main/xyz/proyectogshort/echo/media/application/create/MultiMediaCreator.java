package xyz.proyectogshort.echo.media.application.create;

import xyz.proyectogshort.echo.media.domain.Media;
import xyz.proyectogshort.echo.media.domain.MediaId;
import xyz.proyectogshort.echo.media.domain.MediaRepository;
import xyz.proyectogshort.echo.shared.domain.OrderId;
import xyz.proyectogshort.echo.shared.domain.OrderSource;
import xyz.proyectogshort.echo.shared.domain.OrderSourceUrl;
import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.UuidGenerator;

import java.util.List;
import java.util.stream.LongStream;

@Service
public final class MultiMediaCreator {

    private final UuidGenerator uuidGenerator;
    private final MediaRepository mediaRepository;

    public MultiMediaCreator(UuidGenerator uuidGenerator, MediaRepository mediaRepository) {
        this.uuidGenerator = uuidGenerator;
        this.mediaRepository = mediaRepository;
    }

    public void createMultiple(OrderId orderId, OrderSource orderSource, OrderSourceUrl orderSourceUrl, long orderMediaCount) {

        List<Media> mediaList = LongStream
                .rangeClosed(1, orderMediaCount)
                .mapToObj((i) -> createMedia(i, orderId, orderSource, orderSourceUrl))
                .toList();

        mediaList.forEach(mediaRepository::save);
        mediaList.forEach(System.out::println);
    }

    private Media createMedia(long mediaOrder, OrderId orderId, OrderSource orderSource, OrderSourceUrl orderSourceUrl) {
        MediaId mediaId = new MediaId(uuidGenerator.generate());
        return Media.create(mediaId, mediaOrder, orderId, orderSource, orderSourceUrl);
    }
}
