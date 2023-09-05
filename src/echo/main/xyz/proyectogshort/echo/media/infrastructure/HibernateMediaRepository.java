package xyz.proyectogshort.echo.media.infrastructure;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import xyz.proyectogshort.echo.media.domain.Media;
import xyz.proyectogshort.echo.media.domain.MediaRepository;
import xyz.proyectogshort.shared.domain.Service;

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
}
