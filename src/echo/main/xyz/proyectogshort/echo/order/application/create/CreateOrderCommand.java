package xyz.proyectogshort.echo.order.application.create;

import xyz.proyectogshort.shared.domain.bus.command.Command;

public record CreateOrderCommand(String id, String sourceUrl) implements Command {}
