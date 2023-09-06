package xyz.proyectogshort.echo.media.infrastructure.fetchers.youtube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public final class YoutubeMediaInfo {
    private String title;
    private String uploader;
    @JsonProperty("webpage_url")
    private String webpageUrl;
}
