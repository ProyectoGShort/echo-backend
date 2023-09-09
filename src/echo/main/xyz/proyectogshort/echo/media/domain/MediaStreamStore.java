package xyz.proyectogshort.echo.media.domain;

import java.io.InputStream;

public interface MediaStreamStore {
    void store(InputStream mediaStream) throws MediaStoreException;
}
