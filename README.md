# Realnaut assessment

## Overview

This repository contains a solution of the Realnaut's assessment about movies and shows spaceships, made by Daniel Arqués Toro.

## Guidelines

1. Clone this repository
2. `cd src/main/resources/docker`
3. `docker compose up`
4. From project's root: `mvn spring-boot:run`
5. Go to http://localhost:8080/swagger-ui/index.html
6. Actuator endpoint: http://localhost:8080/actuator

### For testing kafka producer/consumer:

1. Run `curl --location 'http://localhost:8080/spaceship' \
--header 'Content-Type: application/json' \
--data '{
    "id": 100,
    "name": "Endurance",
    "show": "Interstellar"
}'`
2. Check logs in order to see log.info
