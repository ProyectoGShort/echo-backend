package xyz.proyectogshort.echo.media.infrastructure.downloaders.store;

import xyz.proyectogshort.echo.media.domain.Media;
import xyz.proyectogshort.shared.domain.Service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class LocalMediaStreamStore implements MediaStreamStore {
    @Override
    public MediaStream getOutputStream(Media media) throws IOException {
        String contentPath = String.format("/media_store/%s/%s.mp3", media.getId().value(), media.getTitle());
        String path = "/tmp/echo" + contentPath;

        File file = new File(path);
        file.getParentFile().mkdirs();
        file.createNewFile();

        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file, false));
        return new MediaStream(outputStream, contentPath);
    }
}
