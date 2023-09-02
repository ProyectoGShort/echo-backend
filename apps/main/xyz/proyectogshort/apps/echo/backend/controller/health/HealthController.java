package xyz.proyectogshort.apps.echo.backend.controller.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController()
public class HealthController {

    @GetMapping("/health")
    public HashMap<String, String> index() {
        HashMap<String, String> status = new HashMap<>();
        status.put("application", "echo_backend");
        status.put("status", "ok");

        return status;
    }
}
