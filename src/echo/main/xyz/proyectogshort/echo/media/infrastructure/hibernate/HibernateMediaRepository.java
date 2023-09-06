package xyz.proyectogshort.echo.media.infrastructure.hibernate;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import xyz.proyectogshort.echo.media.domain.*;
import xyz.proyectogshort.echo.shared.domain.OrderId;
import xyz.proyectogshort.echo.shared.domain.OrderSource;
import xyz.proyectogshort.echo.shared.domain.OrderSourceUrl;
import xyz.proyectogshort.shared.domain.Service;

import java.util.List;
import java.util.Optional;

@Service
public final class HibernateMediaRepository implements MediaRepository {

    private final ModelMapper modelMapper;
    private final MediaEntityRepository mediaEntityRepository;

    public HibernateMediaRepository(MediaEntityRepository mediaEntityRepository) {
        this.mediaEntityRepository = mediaEntityRepository;

        modelMapper = new ModelMapper();
        modelMapper
                .getConfiguration()
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true)
                .setAmbiguityIgnored(true);
    }

    @Override
    public void save(Media media) {
        MediaEntity mediaEntity = modelMapper.map(media, MediaEntity.class);
        mediaEntity.setMediaSourceUrlValue(media.getMediaSourceUrlValue());

        mediaEntityRepository.save(mediaEntity);
    }

    @Override
    public List<Media> searchByOrderId(OrderId orderId) {
        var result = mediaEntityRepository.findByOrderIdValue(orderId.value());
        return result
                .stream()
                .map(this::mapFromEntity)
                .toList();
    }

    @Override
    public Optional<Media> findById(MediaId mediaId) {
        return mediaEntityRepository
                .findById(mediaId.value())
                .map(this::mapFromEntity);
    }

    private Media mapFromEntity(MediaEntity mediaEntity) {
        return new Media(
                new MediaId(mediaEntity.getIdValue()),
                mediaEntity.getMediaOrder(),
                mediaEntity.getTitle(),
                mediaEntity.getAuthor(),
                mediaEntity.getMediaSourceUrlValue() == null ? null : new MediaSourceUrl(mediaEntity.getMediaSourceUrlValue()),
                MediaStatus.valueOf(mediaEntity.getMediaStatus()),
                new OrderId(mediaEntity.getOrderIdValue()),
                OrderSource.valueOf(mediaEntity.getOrderSource()),
                new OrderSourceUrl(mediaEntity.getOrderSourceUrlValue())
        );
    }
}
