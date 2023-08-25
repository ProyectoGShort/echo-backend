package xyz.proyectogshort.test.test.infrastructure.hibernate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestEntityRepository extends JpaRepository<TestEntity, String> {}