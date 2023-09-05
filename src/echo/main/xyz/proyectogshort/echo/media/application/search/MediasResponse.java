package xyz.proyectogshort.echo.media.application.search;

import xyz.proyectogshort.echo.media.domain.Media;
import xyz.proyectogshort.shared.domain.bus.query.Response;

import java.util.List;

public record MediasResponse(List<MediaResponse> medias) implements Response {

    public static MediasResponse fromAggregates(List<Media> medias) {
        var list = medias.stream().map(MediaResponse::fromAggregate).toList();
        return new MediasResponse(list);
    }
}
