package com.daniarques.realnaut_spaceships.domain;

import com.daniarques.realnaut_spaceships.domain.model.Spaceship;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {

    @KafkaListener(topics = "spaceship_creation_topic", groupId = "spaceship-group")
    public void consumeMessage(final Spaceship spaceship) {
        log.info("Received Spaceship creation: " + spaceship);
    }
}