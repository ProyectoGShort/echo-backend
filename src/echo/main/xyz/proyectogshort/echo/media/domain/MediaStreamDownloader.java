package xyz.proyectogshort.echo.media.domain;

import java.io.InputStream;

public interface MediaStreamDownloader {
    InputStream download(Media media) throws MediaDownloadException;
    boolean isCompatible(Media media);
}
