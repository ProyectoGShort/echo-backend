package xyz.proyectogshort.shared.infrastructure;

import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.UuidGenerator;

import java.util.UUID;

@Service
public final class JavaUuidGenerator implements UuidGenerator {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}