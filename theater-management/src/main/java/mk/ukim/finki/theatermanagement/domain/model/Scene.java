package mk.ukim.finki.theatermanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "scenes")
@Getter
@Setter
@NoArgsConstructor
public class Scene extends AbstractEntity<SceneId> {
    @EmbeddedId
    private SceneId id;

    @Version
    private Long version;

    private String name;

    private int capacity;

    @Column(name = "seats_in_row")
    private int seatsInRow;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "scene", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Show> shows = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "scene", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Seat> seats = new HashSet<>();

    private Scene(String name, int capacity, int seatsInRow){
        this.id = DomainObjectId.randomId(SceneId.class);
        this.name = name;
        this.capacity = capacity;
        this.seatsInRow = seatsInRow;
    }


    public Scene createScene(String name, int capacity, int seatsInRow){
        return new Scene(name,capacity,seatsInRow);
    }
}
