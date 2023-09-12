package xyz.proyectogshort.echo.media.infrastructure.downloaders;

import xyz.proyectogshort.echo.media.domain.Media;
import xyz.proyectogshort.echo.media.domain.MediaDownloadException;
import xyz.proyectogshort.echo.media.domain.MediaDownloader;
import xyz.proyectogshort.echo.media.infrastructure.downloaders.store.MediaStreamStore;
import xyz.proyectogshort.echo.shared.domain.Source;
import xyz.proyectogshort.shared.domain.Service;

import java.io.*;
import java.util.List;

@Service
public final class YoutubeMediaDownloader implements MediaDownloader {

    private final MediaStreamStore mediaStreamStore;

    public YoutubeMediaDownloader(MediaStreamStore mediaStreamStore) {
        this.mediaStreamStore = mediaStreamStore;
    }

    @Override
    public boolean isCompatible(Media media) {
        return media.getSource().equals(Source.YOUTUBE);
    }

    @Override
    public String download(Media media) throws MediaDownloadException {
        String temporalPath = getTemporalPath(media);
        MediaStreamStore.MediaStream mediaStream;

        try {
            Process process = createProcess(media, temporalPath);
            ensureProcessIsCompletedWithoutErrors(process);

            mediaStream = mediaStreamStore.getOutputStream(media);
            storeTemporalFile(temporalPath, mediaStream);

        } catch (Exception e) {
            new File(temporalPath).delete();
            throw new MediaDownloadException(e);
        }

        new File(temporalPath).delete();
        return mediaStream.contentPath();
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

    private static void ensureProcessIsCompletedWithoutErrors(Process process) throws Exception {
        try(var errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {

            if (process.waitFor() != 0) {
                String error = String.join(System.lineSeparator(), errorStream.lines().toList());
                throw new Exception(error);
            }
        }
    }

    private static void storeTemporalFile(
            String temporalPath, MediaStreamStore.MediaStream mediaStream) throws IOException {

        try(
                var inputStream = new BufferedInputStream(new FileInputStream(temporalPath));
                var outputStream = mediaStream.outputStream()
        ) {
            inputStream.transferTo(outputStream);
        }
    }
}
