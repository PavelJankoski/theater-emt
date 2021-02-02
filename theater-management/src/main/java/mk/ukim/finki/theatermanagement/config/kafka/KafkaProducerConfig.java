package mk.ukim.finki.theatermanagement.config.kafka;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import mk.ukim.finki.sharedkernel.domain.dto.kafka.ReservationSeatsForShowDTO;
import mk.ukim.finki.sharedkernel.domain.dto.kafka.SceneSeatsDTO;
import mk.ukim.finki.theatermanagement.domain.model.Scene;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {


    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    //Create reservations when show is created
    @Bean
    public ProducerFactory<String, ReservationSeatsForShowDTO> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, ReservationSeatsForShowDTO> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }


    //Create seats when scene is created
    @Bean
    public  ProducerFactory<String, Scene> producerFactoryScene(){
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }
    @Bean
    public KafkaTemplate<String,Scene> kafkaTemplateScene(){
        return new KafkaTemplate<>(producerFactoryScene());
    }


    //Delete all reservations when show is deleted
    @Bean
    public  ProducerFactory<String, String> producerFactoryDeleteShow(){
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }
    @Bean
    public KafkaTemplate<String, String> kafkaTemplateDeleteShow(){
        return new KafkaTemplate<>(producerFactoryDeleteShow());
    }

}
