package xyz.proyectogshort.echo.media.application.search;

import xyz.proyectogshort.echo.shared.domain.OrderId;
import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.bus.query.QueryHandler;

@Service
public class SearchMediasQueryHandler implements QueryHandler<SearchMediasQuery, MediasResponse> {

    private final MediaSearcher mediaSearcher;

    public SearchMediasQueryHandler(MediaSearcher mediaSearcher) {
        this.mediaSearcher = mediaSearcher;
    }

    @Override
    public MediasResponse handle(SearchMediasQuery query) {
        return mediaSearcher.search(new OrderId(query.orderId()));
    }
}
