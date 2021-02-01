package mk.ukim.finki.theatermanagement.application.service.impl;

import mk.ukim.finki.sharedkernel.config.KafkaTopics;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.sharedkernel.domain.dto.kafka.ReservationSeatsForShowDTO;
import mk.ukim.finki.sharedkernel.domain.dto.kafka.SceneSeatsDTO;
import mk.ukim.finki.theatermanagement.application.service.ShowService;
import mk.ukim.finki.theatermanagement.domain.model.Scene;
import mk.ukim.finki.theatermanagement.domain.model.Show;
import mk.ukim.finki.theatermanagement.domain.model.ShowId;
import mk.ukim.finki.theatermanagement.domain.repository.SceneRepository;
import mk.ukim.finki.theatermanagement.domain.repository.ShowRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowServiceImpl implements ShowService {

    private final ShowRepository showRepository;
    private final KafkaTemplate<String, ReservationSeatsForShowDTO> kafkaTemplate;

    public ShowServiceImpl(ShowRepository showRepository, KafkaTemplate<String, ReservationSeatsForShowDTO> kafkaTemplate) {
        this.showRepository = showRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    @Override
    public Show createShow(Show show) {
        List<String> seatIds = show.getScene().getSeats().stream().map(s -> s.getId().getId()).collect(Collectors.toList());
        show.setId(DomainObjectId.randomId(ShowId.class));
        ReservationSeatsForShowDTO dto = new ReservationSeatsForShowDTO(show.getId().getId(), seatIds,show.getTicketPrice());
        showRepository.saveAndFlush(show);
        kafkaTemplate.send(KafkaTopics.RESERVATION_SEATS_FOR_SHOW, dto);
        return show;
    }

}
