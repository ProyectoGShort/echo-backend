package xyz.proyectogshort.echo.media.domain;

public final class MediaInfoFetcherNotFoundException extends RuntimeException {
    public MediaInfoFetcherNotFoundException(Media media) {
        super(String.format("No MediaInfo fetcher found for %s", media.getId().value()));
    }
}
