package com.madani.devops_api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DevopsApiApplication {
	private static final Logger logger = LoggerFactory.getLogger(DevopsApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DevopsApiApplication.class, args);
	}
	
	
	@GetMapping("/")
    public String home() {
        logger.info("Accès à la page d'accueil"); // Log structuré simple
        return "Bienvenue sur mon projet DevOps !";
    }

    @GetMapping("/api/hello")
    public String sayHello() {
        logger.info("Requête reçue sur /api/hello");
        return "Bonjour le monde ! Ceci est mon API REST.";
    }

    @GetMapping("/api/error")
    public String triggerError() {
        logger.error("Oups! Une erreur a été déclenchée volontairement.");
        throw new RuntimeException("Erreur de test DevOps");
    }
	

}
