# Realnaut assessment

## Overview

This repository contains a solution of the Realnaut's assessment about movies and shows spaceships, made by Daniel Arqués Toro.

## Guidelines

1. Clone this repository
2. `cd src/main/resources/docker`
3. `docker compose up`
4. `mvn spring-boot:run`
4. Go to http://localhost:8080/swagger-ui/index.html
5. Actuator endpoint: http://localhost:8080/actuator

### For testing kafka producer/cosumer:

1. Run `curl --location 'http://localhost:8080/spaceship' \
--header 'Content-Type: application/json' \
--data '{
    "id": 100,
    "name": "Endurance",
    "show": "Interstellar"
}'`
2. Check logs in order to see log.info
