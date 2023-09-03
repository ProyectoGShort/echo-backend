package xyz.proyectogshort.echo.order.domain;

public class OrderInfoFetchingException extends RuntimeException {
    public OrderInfoFetchingException(Exception e) {
        super(e);
    }
}
