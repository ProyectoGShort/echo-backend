package xyz.proyectogshort.echo.media.domain;

public final class MediaDownloadException extends RuntimeException {
    public MediaDownloadException(Exception e) {
        super(e);
    }

    public MediaDownloadException(String e) {
        super(e);
    }
}
