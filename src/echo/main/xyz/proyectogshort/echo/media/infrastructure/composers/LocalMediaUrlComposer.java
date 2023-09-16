package xyz.proyectogshort.echo.media.infrastructure.composers;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import xyz.proyectogshort.echo.media.domain.MediaUrlComposer;
import xyz.proyectogshort.echo.media.domain.Media;
import xyz.proyectogshort.shared.domain.Service;

import java.io.File;

@Service
@ConditionalOnProperty(value = "echo.media.store", havingValue = "local")
public class LocalMediaUrlComposer implements MediaUrlComposer {
    @Override
    public String composeURL(Media media) {
        var path = String.format("/tmp/echo/%s", media.getContentPath());
        return new File(path).toURI().toString();
    }
}
