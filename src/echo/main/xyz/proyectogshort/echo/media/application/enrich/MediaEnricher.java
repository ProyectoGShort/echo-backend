package xyz.proyectogshort.echo.media.application.enrich;

import xyz.proyectogshort.echo.media.domain.*;
import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.bus.event.EventBus;

@Service
public final class MediaEnricher {
    private final MediaInfoFetcherGateway mediaInfoFetcherGateway;
    private final MediaFinder mediaFinder;
    private final MediaRepository mediaRepository;
    private final EventBus eventBus;

    public MediaEnricher(MediaInfoFetcherGateway mediaInfoFetcherGateway, MediaFinder mediaFinder, MediaRepository mediaRepository, EventBus eventBus) {
        this.mediaInfoFetcherGateway = mediaInfoFetcherGateway;
        this.mediaFinder = mediaFinder;
        this.mediaRepository = mediaRepository;
        this.eventBus = eventBus;
    }

    public void enrich(MediaId mediaId) {
        Media media = mediaFinder.findOrThrow(mediaId);

        MediaInfo mediaInfo = mediaInfoFetcherGateway.fetch(media);

        media.updateWithMediaInfo(mediaInfo);

        mediaRepository.save(media);
        eventBus.publish(media.pullDomainEvents());
    }
}
