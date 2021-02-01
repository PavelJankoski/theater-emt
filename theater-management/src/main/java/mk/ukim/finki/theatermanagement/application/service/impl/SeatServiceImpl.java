package mk.ukim.finki.theatermanagement.application.service.impl;

import mk.ukim.finki.sharedkernel.config.KafkaTopics;
import mk.ukim.finki.theatermanagement.application.service.SeatService;
import mk.ukim.finki.theatermanagement.domain.model.Scene;
import mk.ukim.finki.theatermanagement.domain.model.Seat;
import mk.ukim.finki.theatermanagement.domain.repository.SeatRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

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

//        for(int i = 1;i<scene.getCapacity()+1;i++){
//            Seat seat = new Seat();
//            if(i%scene.getSeatsInRow()!=0){
//                seat.setSeatNo(i%seatsInRow);
//                seat.setSeatRow(((i/seatsInRow) + 1));
//            }
//            else{
//                seat.setSeatNo(seatsInRow);
//                seat.setSeatRow((i/seatsInRow));
//            }
//
//            seat.setTheScene(scene);
//            scene.getSeats().add(seat);
//
//        }
//        return this.sceneRepository.save(scene);
//    }
        return null;
    }

}
