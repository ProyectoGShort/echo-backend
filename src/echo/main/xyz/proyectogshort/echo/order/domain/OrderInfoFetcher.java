package xyz.proyectogshort.echo.order.domain;

public interface OrderInfoFetcher {
    boolean isCompatible(OrderSourceUrl orderSourceUrl);
    OrderInfo fetch(OrderSourceUrl orderSourceUrl);
}
