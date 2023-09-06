package xyz.proyectogshort.echo.media.domain;

public final class MediaNotFoundException extends RuntimeException {
    public MediaNotFoundException(MediaId mediaId) {
        super(String.format("Media %s not found", mediaId.value()));
    }
}
