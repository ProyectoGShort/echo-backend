package xyz.proyectogshort.echo.media.application.search;

import xyz.proyectogshort.echo.media.domain.Media;

public record MediaResponse(
        String id,
        long position,
        String source,
        String sourceUrl,
        String status,
        String title,
        String author,
        String url
) {
    public static MediaResponse fromAggregate(Media media, String url) {
        return new MediaResponse(
                media.getId().value(),
                media.getPosition(),
                media.getSource().name(),
                media.getMediaSourceUrlValueOrNull(),
                media.getStatus().name(),
                media.getTitle(),
                media.getAuthor(),
                url
        );
    }
}
