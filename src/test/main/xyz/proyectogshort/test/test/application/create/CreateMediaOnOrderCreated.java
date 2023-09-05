package xyz.proyectogshort.test.test.application.create;

import xyz.proyectogshort.echo.shared.domain.OrderCreatedEvent;
import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.bus.event.EventListener;
import xyz.proyectogshort.test.test.infrastructure.hibernate.TestEntity;
import xyz.proyectogshort.test.test.infrastructure.hibernate.TestEntityRepository;

import java.util.UUID;

@Service
public class CreateMediaOnOrderCreated {

    private final TestEntityRepository testEntityRepository;

    public CreateMediaOnOrderCreated(TestEntityRepository testEntityRepository) {
        this.testEntityRepository = testEntityRepository;
    }

    @EventListener()
    public void on(OrderCreatedEvent event) {
        testEntityRepository.save(new TestEntity(UUID.randomUUID().toString()));
//        throw new RuntimeException();
    }

}
