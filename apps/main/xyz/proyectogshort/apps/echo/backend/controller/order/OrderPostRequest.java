package xyz.proyectogshort.apps.echo.backend.controller.order;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.UUID;

public record OrderPostRequest(
        @NotBlank @UUID String id,
        @URL @NotBlank String sourceUrl
) {}
