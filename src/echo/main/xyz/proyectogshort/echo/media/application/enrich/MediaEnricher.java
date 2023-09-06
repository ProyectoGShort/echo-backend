package xyz.proyectogshort.echo.media.application.enrich;

import xyz.proyectogshort.echo.media.domain.*;
import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.bus.event.EventBus;

@Service
public final class MediaEnricher {
    private final MediaInfoFetcherGateway mediaInfoFetcherGateway;
    private final MediaRepository mediaRepository;
    private final EventBus eventBus;

    public MediaEnricher(MediaInfoFetcherGateway mediaInfoFetcherGateway, MediaRepository mediaRepository, EventBus eventBus) {
        this.mediaInfoFetcherGateway = mediaInfoFetcherGateway;
        this.mediaRepository = mediaRepository;
        this.eventBus = eventBus;
    }

    public void enrich(MediaId mediaId) {
        Media media = mediaRepository
                .findById(mediaId)
                .orElseThrow(() -> new MediaNotFoundException(mediaId));

        MediaInfo mediaInfo = mediaInfoFetcherGateway.fetch(media);

        media.updateWithMediaInfo(mediaInfo);

        mediaRepository.save(media);
        eventBus.publish(media.pullDomainEvents());
    }
}
