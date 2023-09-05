package xyz.proyectogshort.echo.order.domain;

import xyz.proyectogshort.echo.shared.domain.OrderSourceUrl;

public interface OrderInfoFetcher {
    boolean isCompatible(OrderSourceUrl orderSourceUrl);
    OrderInfo fetch(OrderSourceUrl orderSourceUrl) throws OrderInfoFetchingException;
}
