package xyz.proyectogshort.echo.order.application.find;

import xyz.proyectogshort.shared.domain.bus.query.Query;

public record FindOrderQuery(String id) implements Query {}
