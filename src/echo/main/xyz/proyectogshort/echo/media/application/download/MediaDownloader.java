package xyz.proyectogshort.echo.media.application.download;

import xyz.proyectogshort.echo.media.domain.*;
import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.bus.event.EventBus;

@Service
public final class MediaDownloader {
    private final MediaStreamDownloaderGateway mediaStreamDownloaderGateway;
    private final MediaStreamStore mediaStreamStore;
    private final MediaFinder mediaFinder;
    private final MediaRepository mediaRepository;
    private final EventBus eventBus;

    public MediaDownloader(
            MediaStreamDownloaderGateway mediaStreamDownloaderGateway,
            MediaStreamStore mediaStreamStore,
            MediaFinder mediaFinder,
            MediaRepository mediaRepository,
            EventBus eventBus
    ) {
        this.mediaStreamDownloaderGateway = mediaStreamDownloaderGateway;
        this.mediaStreamStore = mediaStreamStore;
        this.mediaFinder = mediaFinder;
        this.mediaRepository = mediaRepository;
        this.eventBus = eventBus;
    }

    public void download(MediaId mediaId) {
        var media = mediaFinder.findOrThrow(mediaId);
        var mediaStream = mediaStreamDownloaderGateway.download(media);
        mediaStreamStore.store(mediaStream);

        media.markAsDownloaded();
        mediaRepository.save(media);
        eventBus.publish(media.pullDomainEvents());
    }
}
