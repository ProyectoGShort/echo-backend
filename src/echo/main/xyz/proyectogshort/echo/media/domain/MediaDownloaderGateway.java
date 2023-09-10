package xyz.proyectogshort.echo.media.domain;

import xyz.proyectogshort.shared.domain.Service;

import java.util.List;

@Service
public final class MediaDownloaderGateway {

    private final List<MediaDownloader> mediaDownloaderList;

    public MediaDownloaderGateway(List<MediaDownloader> mediaDownloaderList) {
        this.mediaDownloaderList = mediaDownloaderList;
    }

    public String download(Media media) {
        return mediaDownloaderList
                .stream()
                .filter((downloader) -> downloader.isCompatible(media))
                .findFirst()
                .orElseThrow(() -> new MediaDownloaderNotFoundException(media))
                .download(media);
    }
}
