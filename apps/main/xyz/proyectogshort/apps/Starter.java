package xyz.proyectogshort.apps;

import org.springframework.boot.SpringApplication;
import xyz.proyectogshort.apps.backend.EchoBackend;

public class Starter {

	public static void main(String[] args) {
		SpringApplication.run(EchoBackend.class, args);

	}

}

