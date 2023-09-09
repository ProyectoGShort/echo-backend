package xyz.proyectogshort.echo.media.domain;

import java.io.InputStream;

public interface MediaStreamDownloader {
    boolean isCompatible(Media media);
    InputStream download(Media media) throws MediaDownloadException;
}
