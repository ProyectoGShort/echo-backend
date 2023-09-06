package xyz.proyectogshort.echo.media.domain;

import xyz.proyectogshort.shared.domain.StringValueObject;
import xyz.proyectogshort.shared.domain.Utils;

public final class MediaSourceUrl extends StringValueObject {
    public MediaSourceUrl(String value) {
        super(getValidatedUrl(value));
    }

    private static String getValidatedUrl(String value) {

        if (!Utils.validateUrl(value)) {
            throw new IllegalArgumentException("Url is not valid");
        }

        return value;
    }

    private MediaSourceUrl() {
        super("");
    }

    public boolean matches(String pattern) {
        return value().matches(pattern);
    }
}
