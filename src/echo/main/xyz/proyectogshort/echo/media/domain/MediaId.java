package xyz.proyectogshort.echo.media.domain;

import xyz.proyectogshort.shared.domain.Identifier;

public final class MediaId extends Identifier {
    public MediaId(String value) {
        super(value);
    }

    private MediaId() {}
}
