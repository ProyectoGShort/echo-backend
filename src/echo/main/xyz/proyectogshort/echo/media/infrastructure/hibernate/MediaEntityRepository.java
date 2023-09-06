package xyz.proyectogshort.echo.media.infrastructure.hibernate;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaEntityRepository extends JpaRepository<MediaEntity, String> {
    List<MediaEntity> findByOrderIdValue(String orderId);
}
