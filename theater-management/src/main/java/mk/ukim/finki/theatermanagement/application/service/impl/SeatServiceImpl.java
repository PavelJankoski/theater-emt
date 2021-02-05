package mk.ukim.finki.theatermanagement.application.service.impl;

import mk.ukim.finki.sharedkernel.config.KafkaTopics;
import mk.ukim.finki.theatermanagement.application.service.SeatService;
import mk.ukim.finki.theatermanagement.domain.model.Scene;
import mk.ukim.finki.theatermanagement.domain.model.Seat;
import mk.ukim.finki.theatermanagement.domain.repository.SeatRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }


    @KafkaListener(topics = KafkaTopics.SCENE_SEATS,
            groupId = "scene_group",
            containerFactory = "sceneKafkaListenerContainerFactory")
    @Override
    public List<Seat> createSeatsForScene(@Payload Scene scene) {
        List<Seat> seats = new ArrayList<>();
        for(int i = 0;i<scene.getCapacity();i++){
            Seat seat = new Seat();
            seat.setScene(scene);
            seats.add(seat);
        }
        return this.seatRepository.saveAll(seats);

    }

}
