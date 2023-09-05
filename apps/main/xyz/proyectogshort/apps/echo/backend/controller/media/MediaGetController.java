package xyz.proyectogshort.apps.echo.backend.controller.media;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xyz.proyectogshort.echo.media.application.search.MediaResponse;
import xyz.proyectogshort.echo.media.application.search.MediasResponse;
import xyz.proyectogshort.echo.media.application.search.SearchMediasQuery;
import xyz.proyectogshort.shared.domain.bus.query.QueryBus;

import java.util.List;

@RestController
public final class MediaGetController {

    private final QueryBus queryBus;

    public MediaGetController(QueryBus queryBus) {
        this.queryBus = queryBus;
    }

    @GetMapping("/orders/{id}/medias")
    public List<MediaResponse> search(@PathVariable("id") String id) {
        return queryBus.<MediasResponse>ask(new SearchMediasQuery(id)).medias();
    }
}
