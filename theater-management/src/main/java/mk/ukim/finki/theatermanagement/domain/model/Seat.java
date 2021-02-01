package mk.ukim.finki.theatermanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.sharedkernel.domain.financial.Money;

import javax.persistence.*;

@Entity
@Table(name = "seats")
@Getter
public class Seat extends AbstractEntity<SeatId> {
    @EmbeddedId
    private SeatId id;

    @Version
    private Long version;

    @Column(name = "seat_row", nullable = false)
    private int seatRow;

    @Column(name = "seat_number", nullable = false)
    private int seatNo;

    @ManyToOne
    @JoinColumn(name = "scene_id")
    @JsonIgnore
    private Scene scene;

   
    
    public Seat(){
        this.id= DomainObjectId.randomId(SeatId.class);
    }

}
