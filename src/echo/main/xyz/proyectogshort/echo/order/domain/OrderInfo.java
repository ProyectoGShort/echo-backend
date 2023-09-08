package xyz.proyectogshort.echo.order.domain;

import xyz.proyectogshort.echo.shared.domain.Source;

public record OrderInfo(String title, Source source, long mediaCount, String author ) {}
