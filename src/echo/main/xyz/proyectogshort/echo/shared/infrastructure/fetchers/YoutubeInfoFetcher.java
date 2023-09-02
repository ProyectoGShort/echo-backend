package xyz.proyectogshort.echo.shared.infrastructure.fetchers;

import xyz.proyectogshort.echo.order.domain.*;
import xyz.proyectogshort.shared.domain.Service;

@Service
public class YoutubeInfoFetcher implements OrderInfoFetcher {
    @Override
    public boolean isCompatible(OrderSourceUrl orderSourceUrl) {
        return true;
    }

    @Override
    public OrderInfo fetch(OrderSourceUrl orderSourceUrl) {
        return new OrderInfo("Title", OrderSource.YOUTUBE, 0, "author");
    }
}
