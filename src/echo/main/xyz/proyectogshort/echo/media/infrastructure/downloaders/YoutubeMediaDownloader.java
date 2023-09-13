package xyz.proyectogshort.echo.media.infrastructure.downloaders;

import xyz.proyectogshort.echo.media.domain.Media;
import xyz.proyectogshort.echo.media.domain.MediaDownloadException;
import xyz.proyectogshort.echo.media.domain.MediaDownloader;
import xyz.proyectogshort.echo.media.infrastructure.downloaders.store.FileStore;
import xyz.proyectogshort.echo.shared.domain.Source;
import xyz.proyectogshort.shared.domain.Service;

import java.io.*;
import java.util.List;

@Service
public final class YoutubeMediaDownloader implements MediaDownloader {

    private final FileStore fileStore;

    public YoutubeMediaDownloader(FileStore fileStore) {
        this.fileStore = fileStore;
    }

    @Override
    public boolean isCompatible(Media media) {
        return media.getSource().equals(Source.YOUTUBE);
    }

    @Override
    public String download(Media media) throws MediaDownloadException {
        String temporalPath = getTemporalPath(media);
        Process process;

        try {
            process = createProcess(media, temporalPath);
        } catch (IOException e) {
            throw new MediaDownloadException(e);
        }

        try(var errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
            File file = new File(temporalPath);

            if (process.waitFor() != 0) {
                file.delete();

                String error = String.join(System.lineSeparator(), errorStream.lines().toList());
                throw new MediaDownloadException(error);
            }

            String finalPath = fileStore.store(media, file);
            file.delete();
            return finalPath;

        } catch (MediaDownloadException e) {
            throw e;
        } catch (Exception e) {
            throw new MediaDownloadException(e);
        }
    }

    private String getTemporalPath(Media media) {
        return String.format(
                "/tmp/echo/downloaders/%s/%s/%s.mp3",
                getClass().getSimpleName().toLowerCase(),
                media.getId().value(),
                media.getTitle()
        );
    }

    private Process createProcess(Media media, String temporalPath) throws IOException {
        List<String> command = List.of(
                "yt-dlp",
                "-q",
                "--no-warnings",
                "--embed-metadata",
                "--embed-thumbnail",
                "--extract-audio",
                "--audio-format",
                "mp3",
                "-o",
                temporalPath,
                media.getMediaSourceUrl().value()
        );

        return new ProcessBuilder(command).start();
    }
}
