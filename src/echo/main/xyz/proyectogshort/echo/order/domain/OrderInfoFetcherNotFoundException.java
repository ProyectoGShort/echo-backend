package xyz.proyectogshort.echo.order.domain;

import xyz.proyectogshort.echo.shared.domain.OrderSourceUrl;

public final class OrderInfoFetcherNotFoundException extends RuntimeException {
    public OrderInfoFetcherNotFoundException(OrderSourceUrl orderSourceUrl) {
        super(String.format("No OrderInfo fetcher found for %s", orderSourceUrl.value()));
    }
}
