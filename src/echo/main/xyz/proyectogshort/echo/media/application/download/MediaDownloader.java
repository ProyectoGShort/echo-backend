package xyz.proyectogshort.echo.media.application.download;

import xyz.proyectogshort.echo.media.domain.*;
import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.bus.event.EventBus;

@Service
public final class MediaDownloader {
    private final MediaDownloaderGateway mediaDownloaderGateway;
    private final MediaFinder mediaFinder;
    private final MediaRepository mediaRepository;
    private final EventBus eventBus;

    public MediaDownloader(
            MediaDownloaderGateway mediaDownloaderGateway,
            MediaFinder mediaFinder,
            MediaRepository mediaRepository,
            EventBus eventBus
    ) {
        this.mediaDownloaderGateway = mediaDownloaderGateway;
        this.mediaFinder = mediaFinder;
        this.mediaRepository = mediaRepository;
        this.eventBus = eventBus;
    }

    public void download(MediaId mediaId) {
        var media = mediaFinder.findOrThrow(mediaId);
        var path = mediaDownloaderGateway.download(media);

        media.markAsDownloaded();
        mediaRepository.save(media);
        eventBus.publish(media.pullDomainEvents());
    }
}
