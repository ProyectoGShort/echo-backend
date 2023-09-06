package xyz.proyectogshort.echo.media.application.search;

import xyz.proyectogshort.echo.media.domain.MediaRepository;
import xyz.proyectogshort.echo.shared.application.FindOrderQuery;
import xyz.proyectogshort.echo.shared.domain.OrderId;
import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.bus.query.QueryBus;

@Service
public final class MediaSearcher {

    private final MediaRepository mediaRepository;
    private final QueryBus queryBus;

    public MediaSearcher(MediaRepository mediaRepository, QueryBus queryBus) {
        this.mediaRepository = mediaRepository;
        this.queryBus = queryBus;
    }

    public MediasResponse search(OrderId orderId) {
        ensureOrderExits(orderId);

        var medias = mediaRepository.searchByOrderId(orderId);
        return MediasResponse.fromAggregates(medias);
    }

    private void ensureOrderExits(OrderId orderId) {
        this.queryBus.ask(new FindOrderQuery(orderId.value()));
    }
}
