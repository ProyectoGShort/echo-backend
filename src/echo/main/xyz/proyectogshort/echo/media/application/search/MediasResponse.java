package xyz.proyectogshort.echo.media.application.search;

import xyz.proyectogshort.shared.domain.bus.query.Response;

import java.util.List;

public record MediasResponse(List<MediaResponse> medias) implements Response {}
