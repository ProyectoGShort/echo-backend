package xyz.proyectogshort.echo.media.domain;

public final class MediaStreamDownloaderNotFoundException extends RuntimeException{
    public MediaStreamDownloaderNotFoundException(Media media) {
        super(String.format("No MediaStreamDownloader found for %s", media.getId().value()));
    }
}
