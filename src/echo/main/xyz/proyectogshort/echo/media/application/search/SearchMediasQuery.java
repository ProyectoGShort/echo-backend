package xyz.proyectogshort.echo.media.application.search;

import xyz.proyectogshort.shared.domain.bus.query.Query;

public record SearchMediasQuery(String orderId) implements Query {}
