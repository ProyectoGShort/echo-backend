package xyz.proyectogshort.apps.echo.backend;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import xyz.proyectogshort.shared.domain.Service;

@SpringBootApplication
@ComponentScan(
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class),
        value = {"xyz.proyectogshort.apps.echo.backend", "xyz.proyectogshort.shared", "xyz.proyectogshort.echo",/* "xyz.proyectogshort.test"*/}
)
public class EchoBackend{}
