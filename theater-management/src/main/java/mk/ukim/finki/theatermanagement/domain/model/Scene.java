package mk.ukim.finki.theatermanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "scenes")
@Getter
@NoArgsConstructor
public class Scene extends AbstractEntity<SceneId> {
    @EmbeddedId
    private SceneId id;

    @Version
    private Long version;

    private String name;

    private int capacity;

    private int seatsInRow;

    @JsonIgnore
    @OneToMany(mappedBy = "scene", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Show> shows = new HashSet<>();

    @OneToMany(mappedBy = "scene", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Seat> seats = new HashSet<>();

    private Scene(String name, int capacity, int seatsInRow){
        this.name = name;
        this.capacity = capacity;
        this.seatsInRow = seatsInRow;
    }

    public Scene createScene(String name, int capacity, int seatsInRow){
        return new Scene(name,capacity,seatsInRow);
    }
}
