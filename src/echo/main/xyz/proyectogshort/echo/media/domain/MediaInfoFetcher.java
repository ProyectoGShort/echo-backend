package xyz.proyectogshort.echo.media.domain;

public interface MediaInfoFetcher {
    boolean isCompatible(Media media);
    MediaInfo fetch(Media media) throws MediaInfoFetchingException;
}
