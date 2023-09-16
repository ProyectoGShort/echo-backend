package xyz.proyectogshort.echo.media.infrastructure.downloaders.store.local;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import xyz.proyectogshort.echo.media.domain.Media;
import xyz.proyectogshort.echo.media.infrastructure.downloaders.store.FileStore;
import xyz.proyectogshort.shared.domain.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@ConditionalOnProperty(value = "echo.media.store", havingValue = "local")
public class LocalFileStore implements FileStore {
    @Override
    public String store(Media media, File file) throws IOException {
        String contentPath = String.format("media_store/%s/%s.mp3", media.getId().value(), media.getTitle());
        Path destionationPath = Path.of( "/tmp/echo/" + contentPath);
        Path originPath = file.toPath();

        Files.createDirectories(destionationPath.getParent());
        Files.copy(originPath, destionationPath);

        return contentPath;
    }
}
