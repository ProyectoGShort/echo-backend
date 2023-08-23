package xyz.proyectogshort.echo.order.infrastructure.hibernate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, String> {}