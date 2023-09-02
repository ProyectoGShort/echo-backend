package xyz.proyectogshort.echo.order.domain;

public class OrderInfoFetcherNotFoundException extends RuntimeException {
    public OrderInfoFetcherNotFoundException(OrderSourceUrl orderSourceUrl) {
        super(String.format("No OrderInfo fetcher found for %s", orderSourceUrl.value()));
    }
}
