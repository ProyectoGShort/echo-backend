package xyz.proyectogshort.echo.media.domain;

import xyz.proyectogshort.shared.domain.Service;

import java.io.InputStream;
import java.util.List;

@Service
public final class MediaStreamDownloaderGateway {

    private final List<MediaStreamDownloader> mediaStreamDownloaderList;

    public MediaStreamDownloaderGateway(List<MediaStreamDownloader> mediaStreamDownloaderList) {
        this.mediaStreamDownloaderList = mediaStreamDownloaderList;
    }

    public InputStream download(Media media) {
        return mediaStreamDownloaderList
                .stream()
                .filter((downloader) -> downloader.isCompatible(media))
                .findFirst()
                .orElseThrow(() -> new MediaStreamDownloaderNotFoundException(media))
                .download(media);
    }
}
