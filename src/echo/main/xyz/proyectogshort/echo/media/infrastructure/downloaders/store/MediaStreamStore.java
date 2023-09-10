package xyz.proyectogshort.echo.media.infrastructure.downloaders.store;

import java.io.InputStream;

public interface MediaStreamStore {
    void store(InputStream mediaStream);
}
