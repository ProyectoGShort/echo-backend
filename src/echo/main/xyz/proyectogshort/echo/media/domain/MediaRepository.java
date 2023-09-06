package xyz.proyectogshort.echo.media.domain;

import xyz.proyectogshort.echo.shared.domain.OrderId;

import java.util.List;
import java.util.Optional;

public interface MediaRepository {
    void save(Media media);
    List<Media> searchByOrderId(OrderId orderId);
    Optional<Media> findById(MediaId mediaId);
}
