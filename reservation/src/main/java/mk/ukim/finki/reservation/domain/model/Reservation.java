package mk.ukim.finki.reservation.domain.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.sharedkernel.domain.financial.Money;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "reservations")
@Getter
@NoArgsConstructor
public class Reservation extends AbstractEntity<ReservationId> {

    @EmbeddedId
    private ReservationId id;

    @Version
    private Long version;

    @Column(nullable = false)
    private Instant reservatedOn;

    @Enumerated(EnumType.STRING)
    @Column(name = "reservation_status",nullable = false)
    private ReservationStatus status;

    @Embedded
    @AttributeOverride(name="id",column = @Column(name="user_id",nullable = false))
    private UserId userId;

    @Embedded
    @AttributeOverride(name="id",column = @Column(name="show_id",nullable = false))
    private ShowId showId;

    @Embedded
    @AttributeOverride(name="id",column = @Column(name="seat_id",nullable = false))
    private SeatId seatId;


    public Reservation(UserId userId, ShowId showId, SeatId seatId) {
        super(DomainObjectId.randomId(ReservationId.class));
        this.userId = userId;
        this.showId = showId;
        this.seatId = seatId;
        this.reservatedOn = Instant.now();
    }
}

