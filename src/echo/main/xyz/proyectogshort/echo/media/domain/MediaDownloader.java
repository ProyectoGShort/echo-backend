package xyz.proyectogshort.echo.media.domain;

public interface MediaDownloader {
    boolean isCompatible(Media media);
    String download(Media media) throws MediaDownloadException;
}
