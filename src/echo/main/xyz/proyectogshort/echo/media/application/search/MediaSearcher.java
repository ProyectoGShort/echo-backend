package xyz.proyectogshort.echo.media.application.search;

import xyz.proyectogshort.echo.media.domain.MediaRepository;
import xyz.proyectogshort.echo.shared.domain.OrderId;
import xyz.proyectogshort.shared.domain.Service;

@Service
public final class MediaSearcher {

    private final MediaRepository mediaRepository;

    public MediaSearcher(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public MediasResponse search(OrderId orderId) {
        var medias = mediaRepository.searchByOrderId(orderId);
        return MediasResponse.fromAggregates(medias);
    }
}
