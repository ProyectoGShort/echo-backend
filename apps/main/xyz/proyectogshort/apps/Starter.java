package xyz.proyectogshort.apps;

import org.springframework.boot.SpringApplication;
import xyz.proyectogshort.apps.echo.backend.EchoBackend;

import java.util.HashMap;

public class Starter {

	public static void main(String[] args) {
		if (args.length < 1) {
			throw new RuntimeException("There are not enough arguments");
		}

		String  applicationName = args[0];
		ensureApplicationExist(applicationName);

		Class<?> applicationClass = getApplications().get(applicationName);
		SpringApplication app = new SpringApplication(applicationClass);
		app.run(args);
	}

	private static void ensureApplicationExist(String applicationName) {
		if (!getApplications().containsKey(applicationName)) {
			throw new RuntimeException(String.format(
					"The application <%s> doesn't exist. Valids:\n- %s",
					applicationName,
					String.join("\n- ", getApplications().keySet())
			));
		}
	}

	private static HashMap<String, Class<?>> getApplications() {
		HashMap<String, Class<?>> applications = new HashMap<>();

		applications.put("echo_backend", EchoBackend.class);

		return applications;
	}
}

