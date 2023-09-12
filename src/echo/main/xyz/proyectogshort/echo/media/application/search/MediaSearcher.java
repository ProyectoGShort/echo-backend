package xyz.proyectogshort.echo.media.application.search;

import xyz.proyectogshort.echo.media.domain.MediaUrlComposer;
import xyz.proyectogshort.echo.media.domain.MediaRepository;
import xyz.proyectogshort.echo.shared.application.FindOrderQuery;
import xyz.proyectogshort.echo.shared.domain.OrderId;
import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.bus.query.QueryBus;

@Service
public final class MediaSearcher {

    private final MediaRepository mediaRepository;
    private final QueryBus queryBus;
    private final MediaUrlComposer urlComposer;

    public MediaSearcher(MediaRepository mediaRepository, QueryBus queryBus, MediaUrlComposer urlComposer) {
        this.mediaRepository = mediaRepository;
        this.queryBus = queryBus;
        this.urlComposer = urlComposer;
    }

    public MediasResponse search(OrderId orderId) {
        ensureOrderExits(orderId);

        var medias = mediaRepository.searchByOrderId(orderId);
        var mediaResponses = medias.stream()
                .map(media -> MediaResponse.fromAggregate(media, urlComposer.composeURL(media)))
                .toList();

        return new MediasResponse(mediaResponses);
    }

    private void ensureOrderExits(OrderId orderId) {
        this.queryBus.ask(new FindOrderQuery(orderId.value()));
    }
}
