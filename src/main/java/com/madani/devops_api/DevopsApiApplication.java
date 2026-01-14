package com.madani.devops_api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*; // Importer tout pour POST/RequestBody

@SpringBootApplication
@RestController
public class DevopsApiApplication {
    private static final Logger logger = LoggerFactory.getLogger(DevopsApiApplication.class);
    
    // NOUVEAU : Une variable pour stocker un état (mémoire vive)
    private String messageDuJour = "Bienvenue sur l'API DevOps (Message par défaut)";

    public static void main(String[] args) {
        SpringApplication.run(DevopsApiApplication.class, args);
    }
    
    @GetMapping("/")
    public String home() {
        logger.info("Accès à la page d'accueil");
        return "Bienvenue sur mon projet DevOps !";
    }

    @GetMapping("/api/hello")
    public String sayHello() {
        logger.info("Requête reçue sur /api/hello");
        return "Bonjour le monde ! Ceci est mon API REST.";
    }
    
    
    @GetMapping("/api/message")
    public String getMessage() {
        logger.info("Lecture du message du jour");
        return this.messageDuJour;
    }

 
    @PostMapping("/api/message")
    public String updateMessage(@RequestBody String newMessage) {
        logger.info("Mise à jour du message : ancien='{}', nouveau='{}'", this.messageDuJour, newMessage);
        this.messageDuJour = newMessage;
        return "Message mis à jour avec succès !";
    }

    @GetMapping("/api/error")
    public String triggerError() {
        logger.error("Oups! Une erreur a été déclenchée volontairement.");
        throw new RuntimeException("Erreur de test DevOps");
    }
}