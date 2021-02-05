package mk.ukim.finki.theatermanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.sharedkernel.domain.financial.Money;

import javax.persistence.*;

@Entity
@Table(name = "seats")
@Getter
@Setter
public class Seat extends AbstractEntity<SeatId> {
    @EmbeddedId
    private SeatId id;

    @Version
    private Long version;

    @ManyToOne
    @JoinColumn(name = "scene_id")
    @JsonIgnore
    private Scene scene;

   
    
    public Seat(){
        this.id= DomainObjectId.randomId(SeatId.class);
    }

}
