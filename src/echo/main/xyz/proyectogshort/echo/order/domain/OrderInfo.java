package xyz.proyectogshort.echo.order.domain;

import xyz.proyectogshort.echo.shared.domain.OrderSource;

public record OrderInfo(String title, OrderSource source, long mediaCount, String author ) {}
