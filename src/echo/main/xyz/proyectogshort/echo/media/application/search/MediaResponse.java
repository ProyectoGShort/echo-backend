package xyz.proyectogshort.echo.media.application.search;

import xyz.proyectogshort.echo.media.domain.Media;

public record MediaResponse(
        String id,
        String title,
        String author,
        long mediaOrder,
        String mediaStatus,
        String mediaSourceUrl,
        String orderSource
) {
    public static MediaResponse fromAggregate(Media media) {
        return new MediaResponse(
                media.getId().value(),
                media.getTitle(),
                media.getAuthor(),
                media.getMediaOrder(),
                media.getMediaStatus().name(),
                media.getMediaSourceUrlValue(),
                media.getOrderSource().name()
        );
    }
}
