package xyz.proyectogshort.echo.media.domain;

import xyz.proyectogshort.shared.domain.Service;

import java.util.List;

@Service
public final class MediaInfoFetcherGateway {
    private final List<MediaInfoFetcher> mediaInfoFetchers;

    public MediaInfoFetcherGateway(List<MediaInfoFetcher> mediaInfoFetchers) {
        this.mediaInfoFetchers = mediaInfoFetchers;
    }

    public MediaInfo fetch(Media media) {
        return mediaInfoFetchers
                .stream()
                .filter((fetcher) -> fetcher.isCompatible(media))
                .findFirst()
                .orElseThrow(() -> new MediaInfoFetcherNotFoundException(media))
                .fetch(media);
    }
}
