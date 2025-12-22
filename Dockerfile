# 1. On utilise une image de base plus fiable (Eclipse Temurin Java 17)
FROM eclipse-temurin:17-jdk-alpine

# 2. On copie le fichier .jar généré précédemment dans l'image
# Assure-toi que le nom du fichier correspond à celui dans ton dossier 'target'
COPY target/devops-api-0.0.1-SNAPSHOT.jar app.jar

# 3. On indique la commande pour lancer l'app
ENTRYPOINT ["java", "-jar", "/app.jar"]