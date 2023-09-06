package xyz.proyectogshort.echo.shared.application;

import xyz.proyectogshort.shared.domain.bus.query.Query;

public record FindOrderQuery(String id) implements Query {}
