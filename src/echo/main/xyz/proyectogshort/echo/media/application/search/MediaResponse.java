package xyz.proyectogshort.echo.media.application.search;

import xyz.proyectogshort.echo.media.domain.Media;

public record MediaResponse(
        String id,
        long position,
        String source,
        String status,
        String title,
        String author,
        String mediaSourceUrl
) {
    public static MediaResponse fromAggregate(Media media) {
        return new MediaResponse(
                media.getId().value(),
                media.getPosition(),
                media.getSource().name(),
                media.getStatus().name(),
                media.getTitle(),
                media.getAuthor(),
                media.getMediaSourceUrlValueOrNull()
        );
    }
}
