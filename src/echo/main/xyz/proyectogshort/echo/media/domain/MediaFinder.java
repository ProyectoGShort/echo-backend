package xyz.proyectogshort.echo.media.domain;

import xyz.proyectogshort.shared.domain.Service;

@Service
public final class MediaFinder {
    private final MediaRepository mediaRepository;

    public MediaFinder(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public Media findOrThrow(MediaId mediaId) {
        return mediaRepository
                .findById(mediaId)
                .orElseThrow(() -> new MediaNotFoundException(mediaId));
    }
}
