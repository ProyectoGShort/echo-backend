package xyz.proyectogshort.echo.media.infrastructure.composers;

import org.apache.hc.core5.net.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import xyz.proyectogshort.echo.media.domain.Media;
import xyz.proyectogshort.echo.media.domain.MediaUrlComposer;
import xyz.proyectogshort.shared.domain.Service;

@Service
@ConditionalOnProperty(value = "echo.media.store", havingValue = "s3")
public class CdnMediaUrlComposer implements MediaUrlComposer {

    @Value("${echo.media.store.cdn}")
    private String cdnUrl;

    @Override
    public String composeURL(Media media) {
        return new URIBuilder()
                .setPath(media.getContentPath())
                .setScheme("https")
                .setHost(cdnUrl)
                .toString();
    }
}
