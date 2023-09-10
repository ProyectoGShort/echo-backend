package xyz.proyectogshort.echo.media.infrastructure.downloaders;

import xyz.proyectogshort.echo.media.domain.Media;
import xyz.proyectogshort.echo.media.domain.MediaDownloadException;
import xyz.proyectogshort.echo.media.domain.MediaDownloader;
import xyz.proyectogshort.echo.shared.domain.Source;
import xyz.proyectogshort.shared.domain.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public final class YoutubeMediaDownloader implements MediaDownloader {
    @Override
    public boolean isCompatible(Media media) {
        return media.getSource().equals(Source.YOUTUBE);
    }

    @Override
    public String download(Media media) throws MediaDownloadException {
        try {
            return null;
//            return new BufferedInputStream(startDownload(media));
        } catch (Exception e) {
            throw new MediaDownloadException(e);
        }
    }

    private InputStream startDownload(Media media) throws IOException {
        List<String> command = List.of(
                "yt-dlp",
                "-q",
                "--no-warnings",
                "-o",
                "-",
                media.getMediaSourceUrl().value()
        );

        // TODO ON ERROR
        return new ProcessBuilder(command)
                .redirectErrorStream(true)
                .start()
                .getInputStream();
    }
}
