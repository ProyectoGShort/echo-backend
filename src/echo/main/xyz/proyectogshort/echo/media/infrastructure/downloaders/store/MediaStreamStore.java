package xyz.proyectogshort.echo.media.infrastructure.downloaders.store;

import xyz.proyectogshort.echo.media.domain.Media;

import java.io.BufferedOutputStream;

public interface MediaStreamStore {
    MediaStream getOutputStream(Media media) throws Exception;

    record MediaStream(BufferedOutputStream outputStream, String contentPath) {}
}
