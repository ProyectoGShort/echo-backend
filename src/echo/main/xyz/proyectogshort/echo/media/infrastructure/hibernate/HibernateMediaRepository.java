package xyz.proyectogshort.echo.media.infrastructure.hibernate;

import xyz.proyectogshort.echo.media.domain.Media;
import xyz.proyectogshort.echo.media.domain.MediaId;
import xyz.proyectogshort.echo.media.domain.MediaRepository;
import xyz.proyectogshort.echo.shared.domain.OrderId;
import xyz.proyectogshort.shared.domain.Service;

import java.util.List;
import java.util.Optional;

@Service
public final class HibernateMediaRepository implements MediaRepository {

    private final MediaEntityRepository mediaEntityRepository;

    public HibernateMediaRepository(MediaEntityRepository mediaEntityRepository) {
        this.mediaEntityRepository = mediaEntityRepository;
    }

    @Override
    public void save(Media media) {
        MediaEntity mediaEntity = new MediaEntity(media);
        mediaEntityRepository.save(mediaEntity);
    }

    @Override
    public List<Media> searchByOrderId(OrderId orderId) {
        var result = mediaEntityRepository.findByOrderId(orderId.value());
        return result
                .stream()
                .map(MediaEntity::toMedia)
                .toList();
    }

    @Override
    public Optional<Media> findById(MediaId mediaId) {
        return mediaEntityRepository
                .findById(mediaId.value())
                .map(MediaEntity::toMedia);
    }
}
