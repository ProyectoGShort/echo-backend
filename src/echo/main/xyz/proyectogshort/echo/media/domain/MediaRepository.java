package xyz.proyectogshort.echo.media.domain;

import xyz.proyectogshort.echo.shared.domain.OrderId;

import java.util.List;

public interface MediaRepository {
    void save(Media media);
    List<Media> searchByOrderId(OrderId orderId);
}
