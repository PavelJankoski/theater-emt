package mk.ukim.finki.theatermanagement.application.service;

import mk.ukim.finki.theatermanagement.domain.model.Scene;

import java.util.List;

public interface SceneService {
    List<Scene> findAll();

    Scene findById(String id);

    Scene createScene(Scene scene);
}
