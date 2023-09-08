package xyz.proyectogshort.echo.order.infrastructure.fetchers.youtube;

import com.fasterxml.jackson.databind.ObjectMapper;
import xyz.proyectogshort.echo.order.domain.*;
import xyz.proyectogshort.echo.shared.domain.Source;
import xyz.proyectogshort.echo.shared.domain.OrderSourceUrl;
import xyz.proyectogshort.shared.domain.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Service
public final class YoutubeOrderInfoFetcher implements OrderInfoFetcher {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean isCompatible(OrderSourceUrl orderSourceUrl) {
        return orderSourceUrl.matches(".*(youtube\\.com|youtu\\.be).*");
    }

    @Override
    public OrderInfo fetch(OrderSourceUrl orderSourceUrl) {

        YoutubeOrderInfo youtubeInfo;

        try (BufferedReader inputStream = startDownload(orderSourceUrl)) {

            String result = inputStream
                    .lines()
                    .collect(Collectors.joining());

            youtubeInfo = objectMapper.readValue(result, YoutubeOrderInfo.class);

        } catch (Exception e) {
            throw new OrderInfoFetchingException(e);
        }

        if (youtubeInfo.getPlaylistCount() > 1) {
            return getPlaylistOrder(youtubeInfo);
        }

        return getVideoOrder(youtubeInfo);
    }

    private BufferedReader startDownload(OrderSourceUrl orderSourceUrl) throws IOException {
        List<String> command = List.of("yt-dlp", "--no-download", orderSourceUrl.value(), "-j", "--no-warnings", "-I", "1");

        InputStream stream = new ProcessBuilder(command)
                .redirectErrorStream(true)
                .start()
                .getInputStream();

        return new BufferedReader(new InputStreamReader(stream));
    }

    private static OrderInfo getVideoOrder(YoutubeOrderInfo youtubeInfo) {
        return new OrderInfo(
                youtubeInfo.getTitle(),
                Source.YOUTUBE,
                1,
                youtubeInfo.getUploader()
        );
    }

    private static OrderInfo getPlaylistOrder(YoutubeOrderInfo youtubeInfo) {
        return new OrderInfo(
                youtubeInfo.getPlaylistTitle(),
                Source.YOUTUBE,
                youtubeInfo.getPlaylistCount(),
                youtubeInfo.getPlaylistAuthor()
        );
    }
}
