package xyz.proyectogshort.echo.media.application.search;

import xyz.proyectogshort.echo.media.domain.Media;

public record MediaResponse(
        String id,
        long mediaOrder,
        String mediaStatus,
        String orderSource
) {
    public static MediaResponse fromAggregate(Media media) {
        return new MediaResponse(
                media.getId().value(),
                media.getMediaOrder(),
                media.getMediaStatus().name(),
                media.getOrderSource().name()
        );
    }
}
