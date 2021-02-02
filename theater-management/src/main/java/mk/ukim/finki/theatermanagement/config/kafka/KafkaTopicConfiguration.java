package mk.ukim.finki.theatermanagement.config.kafka;

import mk.ukim.finki.sharedkernel.config.KafkaTopics;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {

    @Bean
    public NewTopic reservationTopic() {
        return TopicBuilder.name(KafkaTopics.RESERVATION_SEATS_FOR_SHOW).build();
    }

    @Bean
    public NewTopic sceneTopic() {
        return TopicBuilder.name(KafkaTopics.SCENE_SEATS).build();
    }

    @Bean
    public NewTopic showDeletedTopic() {
        return TopicBuilder.name(KafkaTopics.DELETE_SHOW).build();
    }


}
