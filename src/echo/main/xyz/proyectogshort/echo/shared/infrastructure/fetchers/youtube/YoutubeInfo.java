package xyz.proyectogshort.echo.shared.infrastructure.fetchers.youtube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class YoutubeInfo {
    private String title;
    private String uploader;
    @JsonProperty(value = "playlist_count", defaultValue = "0")
    private int playlistCount;
    @JsonProperty("playlist_title")
    private String playlistTitle;
    @JsonProperty(value = "playlist_uploader")
    private String playlistAuthor;
}
