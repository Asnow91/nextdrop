# NextDrop

API Spring Boot + pagina Thymeleaf che mostra le **nuove uscite Spotify**.

## Funzionalità
- API: `/api/releases/latest`
- Pagina HTML: `/new-releases`

## Requisiti
- Java 17+
- Maven 3.8+
- Spotify Developer (Client ID/Secret)

## Configurazione
Le credenziali reali sono locali, non in Git.

`src/main/resources/application.properties`
```properties
spring.application.name=nextdrop
spring.config.import=optional:file:./application-secrets.properties

spotify.clientId=${SPOTIFY_CLIENT_ID}
spotify.clientSecret=${SPOTIFY_CLIENT_SECRET}
spotify.defaultCountry=IT
