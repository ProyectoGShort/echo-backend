package xyz.proyectogshort.echo.media.application.enrich;

import xyz.proyectogshort.echo.media.domain.MediaCreatedEvent;
import xyz.proyectogshort.echo.media.domain.MediaId;
import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.bus.event.EventListener;

@Service
public class EnrichMediaOnMediaCreated {

    private final MediaEnricher mediaEnricher;

    public EnrichMediaOnMediaCreated(MediaEnricher mediaEnricher) {
        this.mediaEnricher = mediaEnricher;
    }

    @EventListener
    public void on(MediaCreatedEvent event) {
        mediaEnricher.enrich(new MediaId(event.aggregateId()));
    }
}
