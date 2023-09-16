package xyz.proyectogshort.echo.media.infrastructure.downloaders.store.s3;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import xyz.proyectogshort.echo.media.domain.Media;
import xyz.proyectogshort.echo.media.infrastructure.downloaders.store.FileStore;
import xyz.proyectogshort.shared.domain.Service;

import java.io.File;

@Service
@ConditionalOnProperty(value = "echo.media.store", havingValue = "s3")
public class S3FileStore implements FileStore {

    private final S3Client s3Client;
    private final S3Config s3Config;

    public S3FileStore(S3Client s3Client, S3Config s3Config) {
        this.s3Client = s3Client;
        this.s3Config = s3Config;
    }

    @Override
    public String store(Media media, File file) {
        String contentPath = String.format("media_store/%s/%s", media.getId().value(), media.getTitle());
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(s3Config.getBucketName())
                .key(contentPath)
                .build();

        s3Client.putObject(request, RequestBody.fromFile(file));
        return contentPath;
    }
}
