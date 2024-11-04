package com.daniarques.realnaut_spaceships.domain;

import com.daniarques.realnaut_spaceships.domain.model.Show;
import com.daniarques.realnaut_spaceships.domain.model.Spaceship;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaProducerService {

    private static final String TOPIC = "spaceship_creation_topic";
    private final KafkaTemplate<String, Spaceship> kafkaTemplate;

    public void publishSpaceshipCreatedEvent(final Spaceship spaceship) {
        kafkaTemplate.send(TOPIC, spaceship);
    }
}