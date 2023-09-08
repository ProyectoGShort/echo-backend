package xyz.proyectogshort.echo.media.infrastructure.fetchers.youtube;

import com.fasterxml.jackson.databind.ObjectMapper;
import xyz.proyectogshort.echo.media.domain.*;
import xyz.proyectogshort.echo.shared.domain.Source;
import xyz.proyectogshort.shared.domain.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Service
public final class YoutubeMediaInfoFetcher implements MediaInfoFetcher {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean isCompatible(Media media) {
        return media.matchesSource(Source.YOUTUBE);
    }

    @Override
    public MediaInfo fetch(Media media) {

        YoutubeMediaInfo youtubeInfo;

        try (BufferedReader inputStream = startDownload(media)) {

            String result = inputStream
                    .lines()
                    .collect(Collectors.joining());

            youtubeInfo = objectMapper.readValue(result, YoutubeMediaInfo.class);

        } catch (Exception e) {
            throw new MediaInfoFetchingException(e);
        }

        return new MediaInfo(
                youtubeInfo.getTitle(),
                youtubeInfo.getUploader(),
                new MediaSourceUrl(youtubeInfo.getWebpageUrl())
        );
    }

    private BufferedReader startDownload(Media media) throws IOException {
        List<String> command = List.of(
                "yt-dlp",
                "--no-download",
                media.getOrderSourceUrl().value(),
                "-j",
                "--no-warnings",
                "-I",
                String.valueOf(media.getPosition())
        );

        InputStream stream = new ProcessBuilder(command)
                .redirectErrorStream(true)
                .start()
                .getInputStream();

        return new BufferedReader(new InputStreamReader(stream));
    }
}
