package xyz.proyectogshort.echo.media.application.download;

import xyz.proyectogshort.echo.media.domain.MediaDownloadStartedEvent;
import xyz.proyectogshort.echo.media.domain.MediaId;
import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.bus.event.EventListener;

@Service
public class DownloadMediaOnDownloadStarted {

    private final MediaDownloader mediaDownloader;

    public DownloadMediaOnDownloadStarted(MediaDownloader mediaDownloader) {
        this.mediaDownloader = mediaDownloader;
    }

    @EventListener
    public void on(MediaDownloadStartedEvent event) {
        this.mediaDownloader.download(new MediaId(event.aggregateId()));
    }

}
