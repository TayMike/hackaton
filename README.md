# Hackaton

Postgraduate course completion work

## Overview

This project is a Spring Boot application developed as part of a postgraduate course completion work. It uses Domain-Driven Design (DDD) principles and connects to a MySQL database for production and development environments, with an in-memory H2 database for testing.

## Domain-Driven Design

The DDD model for this project is documented on Miro:\
https://miro.com/app/board/uXjVJezNcEM=/

## Prerequisites

- **Docker** and **Docker Compose** installed.

- Java 21 (for local development or testing).

- Maven (for building the JAR and running tests).

- A `target/*.jar` file built from the project (e.g., `mvn clean package`).

- A `.env` file in the project root with the following variables:

  ```env
  # Production
  DB_URL="jdbc:mysql://mysql:3306/mydatabase"
  DB_USERNAME="myuser"
  DB_PASSWORD="secret"
  
  # Development
  DEV_DB_URL="jdbc:mysql://mysql:3306/mydatabase"
  DEV_DB_USERNAME="myuser"
  DEV_DB_PASSWORD="secret"
  
  # Test
  TEST_DB_URL="jdbc:h2:mem:testdb"
  TEST_DB_USERNAME="sa"
  TEST_DB_PASSWORD=""
  ```

- Ensure `.env` is added to `.gitignore` to avoid committing sensitive data.

## Running the Application with Docker Compose

### Build the Docker Images

Build the images for the application:

```bash
docker-compose build
```

### Run in Production Mode

Start the application with the `prod` profile (uses `application-prod.properties`):

```bash
docker-compose up --build app
```

- Runs the MySQL database and the application on port `8080`.
- To run in the background: `docker-compose up -d --build app`.

### Run in Development Mode

Start the application with the `dev` profile (uses `application-dev.properties`):

```bash
docker-compose up --build app-dev
```

- Runs the MySQL database and the application on port `8081`.
- To run in the background: `docker-compose up -d --build app-dev`.

### Stop and Remove Containers

Stop and remove containers (preserves MySQL data):

```bash
docker-compose down
```

To also remove the MySQL data volume (resets the database):

```bash
docker-compose down -v
```

### View Logs

Check logs for production:

```bash
docker-compose logs -f app
```

Check logs for development:

```bash
docker-compose logs -f app-dev
```

### Run Performance Tests

Run the application with the `dev` profile to execute Gatling performance tests:

```bash
mvn clean gatling:test -Pperformance
```

### Run Unit/Integration Tests

Run tests with the `test` profile (uses `application-test.properties` with H2 database):

```bash
mvn test
```

To run tests in Docker, use a separate `docker-compose-test.yml`:

```bash
docker-compose -f docker-compose-test.yaml up --build
```

## Docker Compose Configuration

The `docker-compose.yml` defines:

- `mysql`: MySQL 8.0 database with `mydatabase` (used by `prod` and `dev` profiles).
- `app`: Production service on port `8080` (uses `SPRING_PROFILES_ACTIVE=prod`).
- `app-dev`: Development service on port `8081` (uses `SPRING_PROFILES_ACTIVE=dev`).

For a separate development database (`devdb`), create a `docker-compose-dev.yml` (see project documentation or contact the maintainer).

## Notes

- Ensure ports `8080`, `8081`, and `3306` are free on your host.
- In production, remove the `3306:3306` port mapping from `mysql` in `docker-compose.yml` for security.
- The `test` profile uses an in-memory H2 database and does not require the MySQL service.
- For debugging, access the MySQL container:

  ```bash
  docker exec -it hackaton-database mysql -u myuser -psecret mydatabase
  ```