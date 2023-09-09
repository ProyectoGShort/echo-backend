package xyz.proyectogshort.echo.media.infrastructure.store;

import xyz.proyectogshort.echo.media.domain.MediaStoreException;
import xyz.proyectogshort.echo.media.domain.MediaStreamStore;
import xyz.proyectogshort.shared.domain.Service;

import java.io.InputStream;

@Service
public class LocalMediaStreamStore implements MediaStreamStore {
    @Override
    public void store(InputStream mediaStream) throws MediaStoreException {
        System.out.println("Stored");
    }
}
