package xyz.proyectogshort.echo.media.infrastructure.downloaders.store.s3;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
@ConditionalOnProperty(value = "echo.media.store", havingValue = "s3")
public class S3Config {

    @Value("${echo.media.store.s3.access-key}")
    private String accessKey;
    @Value("${echo.media.store.s3.secret-key}")
    private String secretKey;
    @Getter
    @Value("${echo.media.store.s3.bucket}")
    private String bucketName;

    @Bean
    protected S3Client s3Client() {
        Region region = Region.US_EAST_1;
        AwsCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);

        return S3Client
                .builder()
                .region(region)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }

}
