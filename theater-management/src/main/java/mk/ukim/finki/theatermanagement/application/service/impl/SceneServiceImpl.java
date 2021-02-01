package mk.ukim.finki.theatermanagement.application.service.impl;

import mk.ukim.finki.sharedkernel.config.KafkaTopics;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.sharedkernel.domain.dto.kafka.SceneSeatsDTO;
import mk.ukim.finki.theatermanagement.application.service.SceneService;
import mk.ukim.finki.theatermanagement.domain.model.Scene;
import mk.ukim.finki.theatermanagement.domain.model.SceneId;
import mk.ukim.finki.theatermanagement.domain.model.ShowId;
import mk.ukim.finki.theatermanagement.domain.repository.SceneRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SceneServiceImpl implements SceneService {


    private final SceneRepository sceneRepository;
    private final KafkaTemplate<String, Scene> kafkaTemplate;

    public SceneServiceImpl(SceneRepository sceneRepository, KafkaTemplate<String, Scene> kafkaTemplate) {
        this.sceneRepository = sceneRepository;
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public Scene createScene(Scene scene) {

        scene.setId(DomainObjectId.randomId(SceneId.class));
        sceneRepository.saveAndFlush(scene);
        kafkaTemplate.send(KafkaTopics.SCENE_SEATS,scene);
        return  scene;
    }
}
