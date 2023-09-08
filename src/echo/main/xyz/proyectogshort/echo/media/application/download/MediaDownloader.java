package xyz.proyectogshort.echo.media.application.download;

import xyz.proyectogshort.echo.media.domain.MediaFinder;
import xyz.proyectogshort.echo.media.domain.MediaId;
import xyz.proyectogshort.echo.media.domain.MediaStreamDownloaderGateway;
import xyz.proyectogshort.shared.domain.Service;

@Service
public final class MediaDownloader {
    private final MediaStreamDownloaderGateway mediaStreamDownloaderGateway;
    private final MediaFinder mediaFinder;

    public MediaDownloader(MediaStreamDownloaderGateway mediaStreamDownloaderGateway, MediaFinder mediaFinder) {
        this.mediaStreamDownloaderGateway = mediaStreamDownloaderGateway;
        this.mediaFinder = mediaFinder;
    }

    public void download(MediaId mediaId) {
        var media = mediaFinder.findOrThrow(mediaId);
        var mediaStream = mediaStreamDownloaderGateway.download(media);
    }
}
