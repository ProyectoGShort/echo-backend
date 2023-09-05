package xyz.proyectogshort.echo.shared.domain;

import xyz.proyectogshort.shared.domain.Identifier;

public final class OrderId extends Identifier {
    public OrderId(String value) {
        super(value);
    }

    private OrderId() {}
}
