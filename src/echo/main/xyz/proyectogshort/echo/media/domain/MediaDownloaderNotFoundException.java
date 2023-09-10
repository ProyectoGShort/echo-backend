package xyz.proyectogshort.echo.media.domain;

public final class MediaDownloaderNotFoundException extends RuntimeException {
    public MediaDownloaderNotFoundException(Media media) {
        super(String.format("No MediaDownloader found for %s", media.getId().value()));
    }
}
