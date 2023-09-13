package xyz.proyectogshort.echo.media.infrastructure.downloaders.store;

import xyz.proyectogshort.echo.media.domain.Media;

import java.io.File;

public interface FileStore {
    String store(Media media, File file) throws Exception;
}
