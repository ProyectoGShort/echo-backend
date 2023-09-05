package xyz.proyectogshort.echo.media.infrastructure;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import xyz.proyectogshort.echo.media.domain.Media;
import xyz.proyectogshort.echo.media.domain.MediaRepository;
import xyz.proyectogshort.echo.shared.domain.OrderId;
import xyz.proyectogshort.shared.domain.Service;

import java.util.List;

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
                .setFieldMatchingEnabled(true);
    }

    @Override
    public void save(Media media) {
        MediaEntity mediaEntity = modelMapper.map(media, MediaEntity.class);
        mediaEntityRepository.save(mediaEntity);
    }

    @Override
    public List<Media> searchByOrderId(OrderId orderId) {
        var result = mediaEntityRepository.findByOrderIdValue(orderId.value());
        return result
                .stream()
                .map(media -> modelMapper.map(media, Media.class))
                .toList();
    }
}
