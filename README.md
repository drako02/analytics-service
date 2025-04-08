# Analytics

Analytics is a Spring Boot application designed to record and analyze click events for a URL shortener service. It consumes click events from Kafka, stores them in a PostgreSQL database, and provides REST endpoints and service methods for querying click statistics.

## Key Components

- **Click Model and Repository**  
  The `Click` entity represents a click event, and the `ClickRepository` (using Spring Data JPA) provides methods to count and retrieve click events by short code and user.

- **Service Layer**  
  The `ClickService` provides business logic to record clicks and retrieve analytics data. It uses the `ClickRepository` for data access.

- **Kafka Consumer**  
  The `ClickEventConsumer` listens to the `redirections` Kafka topic, processes incoming messages, and calls the `ClickService` to record the click.

- **REST Controller**  
  The `ClickController` exposes endpoints to fetch click counts for a short URL and to retrieve click details for a given user.

- **Database Connection Checker**  
  The `DatabaseConnectionChecker` runs at startup to verify that the application can connect to the configured PostgreSQL database.

- **Docker Support**  
  A `Dockerfile` is provided for containerizing the application. The active Spring profile is set to use secret properties, coming from `application-secrets.properties`.

## Prerequisites

- Java 17
- Maven
- PostgreSQL
- Apache Kafka

## Configuration

Configuration is primarily handled through the following properties files, with placeholders for environment variables:

- `src/main/resources/application.properties`  
  Application settings such as server port, Kafka settings, and datasource configuration.

- `src/main/resources/application-secrets.properties`  
  Contains sensitive data like database username and password (this file is excluded via `.gitignore`).

- Additional profiles (e.g. `application-docker.properties`) can be used for environment-specific overlays.

## Running the Application

### Using Maven

From a terminal, run:

```sh
./mvnw spring-boot:run
```

This command compiles the project and starts the Spring Boot application.

### Docker

To build and run the Docker container:

1. Build the jar using Maven:
    ```sh
    ./mvnw clean package
    ```
2. Build the Docker image:
    ```sh
    docker build -t analytics .
    ```
3. Run the container:
    ```sh
    docker run -p 8081:8081 --env DB_USERNAME=drako --env DB_PASSWORD=0000000 analytics
    ```

The Dockerfile uses the `secrets` profile by default and expects environment variables for the database credentials.

## Testing

Unit tests are included in `src/test/java/com/url_shortener/analytics/AnalyticsApplicationTests.java`. To run tests with Maven:

```sh
./mvnw test
```

## File Structure

```
analytics/
├── .gitignore
├── Dockerfile
├── mvnw
├── mvnw.cmd
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com/url_shortener/analytics
│   │   │       ├── AnalyticsApplication.java
│   │   │       ├── config/DatabaseConnectionChecker.java
│   │   │       ├── controller/ClickController.java
│   │   │       ├── consumer/ClickEventConsumer.java
│   │   │       └── service/ClickService.java
│   │   └── resources
│   │       ├── application.properties
│   │       ├── application-docker.properties
│   │       └── application-secrets.properties
│   └── test
│       └── java/com/url_shortener/analytics/AnalyticsApplicationTests.java
├── target
│   └── ... (compiled classes and packaged files)
└── .mvn
    └── wrapper
```

## Additional Notes

- **Logging:**  
  The application uses SLF4J (with a logger configured in each component) to log key actions such as processing click events and checking database connectivity.

- **Environment Variables:**  
  Environment variables such as `DB_USERNAME`, `DB_PASSWORD`, `KAFKA_HOST`, and `DATABASE_URL` are used to provide configuration at runtime.

- **Kafka:**  
  The `ClickEventConsumer` listens to the Kafka topic `redirections` and processes the JSON message containing `shortCode` and `userId`.

This README follows best practices by documenting key functionalities, providing clear instructions for setup, explaining configurations, and outlining project structure.