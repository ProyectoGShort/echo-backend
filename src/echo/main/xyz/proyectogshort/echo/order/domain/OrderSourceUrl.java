package xyz.proyectogshort.echo.order.domain;

import xyz.proyectogshort.shared.domain.StringValueObject;
import xyz.proyectogshort.shared.domain.Utils;

public class OrderSourceUrl extends StringValueObject {
    public OrderSourceUrl(String value) {
        super(getValidatedUrl(value));
    }

    private static String getValidatedUrl(String value) {

        if (!Utils.validateUrl(value)) {
            throw new IllegalArgumentException("Url is not valid");
        }

        return value;
    }

    private OrderSourceUrl() {
        super("");
    }

    public boolean matches(String pattern) {
        return value().matches(pattern);
    }
}
