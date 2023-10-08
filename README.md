# jobs-api
Java Backend Developer Test Repository for Dans Multi Pro

## Prerequisites

- Maven >= 3.9
- Java >= 18
- Docker and Docker Compose to setup local db

## Running

1. Make sure the prerequisites are installed already
2. Run the database using `docker compose up -d` as the config for the local db is in the `docker-compose.yml`
3. Run the Spring Boot App using `mvn spring-boot:run`

## Some assumptions

- The login endpoint will be used to register if the username is not found
- All endpoints except login will require the user to be authenticated already through login
- Since this will only be a GitHub submission and local deployment, I didn't utilize environment variables or gitignoring configs and store the `application.properties` directly
- The validation for username is that it must be 6-20 characters long and can only contain alphabets, numbers, dots, and underscores (like Instagram)
- The validation for password is that it must be 8-20 characters long and must contain at least one of each (lowercase alphabet, uppercase alphabet, number, and special symbols)
