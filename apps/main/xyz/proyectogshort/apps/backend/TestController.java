package xyz.proyectogshort.apps.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.proyectogshort.echo.Test;

@RestController()
public class TestController {

    @GetMapping("/test")
    public Test test() {
        return new Test();
    }
}
