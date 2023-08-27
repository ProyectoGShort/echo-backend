package xyz.proyectogshort.test.test.infrastructure.hibernate.application.create;

import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.test.test.infrastructure.hibernate.TestEntity;
import xyz.proyectogshort.test.test.infrastructure.hibernate.TestEntityRepository;

import java.util.UUID;

@Service
public class TestCreator {
    private final TestEntityRepository testEntityRepository;

    public TestCreator(TestEntityRepository testEntityRepository) {
        this.testEntityRepository = testEntityRepository;
    }

    public void create() {
        this.testEntityRepository.save(new TestEntity(UUID.randomUUID().toString()));
        throw new RuntimeException("Test transaction");
    }
}
