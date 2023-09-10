package xyz.proyectogshort.echo.media.infrastructure.downloaders.store;

import xyz.proyectogshort.shared.domain.Service;

import java.io.InputStream;

@Service
public class LocalMediaStreamStore implements MediaStreamStore {
    @Override
    public void store(InputStream mediaStream) {
        System.out.println("Stored");
    }
}
