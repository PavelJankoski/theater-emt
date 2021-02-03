package mk.ukim.finki.theatermanagement.application.service.impl;

import mk.ukim.finki.sharedkernel.config.KafkaTopics;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.sharedkernel.domain.dto.kafka.ReservationSeatsForShowDTO;
import mk.ukim.finki.theatermanagement.application.service.ShowService;
import mk.ukim.finki.theatermanagement.domain.exceptions.ShowNotFoundException;
import mk.ukim.finki.theatermanagement.domain.model.Show;
import mk.ukim.finki.theatermanagement.domain.model.ShowId;
import mk.ukim.finki.theatermanagement.domain.repository.ShowRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowServiceImpl implements ShowService {

    private final ShowRepository showRepository;
    private final KafkaTemplate<String, ReservationSeatsForShowDTO> kafkaTemplate;
    private final KafkaTemplate<String, String> deleteShowKafkaTemplate;

    public ShowServiceImpl(ShowRepository showRepository, KafkaTemplate<String, ReservationSeatsForShowDTO> kafkaTemplate, KafkaTemplate<String, String> deleteShowKafkaTemplate) {
        this.showRepository = showRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.deleteShowKafkaTemplate = deleteShowKafkaTemplate;
    }

    @Override
    public Page<Show> findAllPaged(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo,pageSize,Sort.by(sortBy));
        return showRepository.findAll(paging);
    }

    @Override
    public List<Show> findAll() {
        return this.showRepository.findAllByIsDeletedOrderByFrom(false);
    }

    @Override
    public Show findShowById(String id) {
        return this.showRepository.findById(new ShowId(id)).orElseThrow(ShowNotFoundException::new);
    }

    @Override
    public List<Show> searchShow(String term) {
        return this.showRepository.findByTitleContainingIgnoreCaseAndIsDeletedFalseOrderByFrom(term);
    }

    @Transactional
    @Override
    public Show createShow(Show show) {
        List<String> seatIds = show.getScene().getSeats().stream().map(s -> s.getId().getId()).collect(Collectors.toList());
        show.setId(DomainObjectId.randomId(ShowId.class));
        ReservationSeatsForShowDTO dto = new ReservationSeatsForShowDTO(show.getId().getId(), seatIds,show.getTicketPrice());
        showRepository.save(show);
        kafkaTemplate.send(KafkaTopics.RESERVATION_SEATS_FOR_SHOW, dto);
        return show;
    }

    @Override
    public Show updateShow(String id, Show show) {
        Show s = findShowById(id);
        s.setTitle(show.getTitle());
        s.setDescription(show.getDescription());
        s.setCostumeDesigner(show.getCostumeDesigner());
        s.setSetDesigner(show.getSetDesigner());
        s.setDuration(show.getDuration());
        s.setFrom(show.getFrom());
        s.setTicketPrice(show.getTicketPrice());
        s.setActors(show.getActors());
        s.setImage(show.getImage());
        if(!s.getScene().getId().getId().equals(show.getScene().getId().getId())){
            s.setScene(show.getScene());
            List<String> seatIds = s.getScene().getSeats().stream().map(sh -> sh.getId().getId()).collect(Collectors.toList());
            ReservationSeatsForShowDTO dto = new ReservationSeatsForShowDTO(s.getId().getId(), seatIds,s.getTicketPrice());
            deleteShowKafkaTemplate.send(KafkaTopics.DELETE_SHOW, id);
            kafkaTemplate.send(KafkaTopics.RESERVATION_SEATS_FOR_SHOW, dto);
        }
        return this.showRepository.save(s);
    }

    @Override
    public void deleteShow(String id) {
        Show s = findShowById(id);
        s.setIsDeleted(true);
        showRepository.save(s);
        deleteShowKafkaTemplate.send(KafkaTopics.DELETE_SHOW, id);
    }

}
