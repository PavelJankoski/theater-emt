package mk.ukim.finki.sharedkernel.domain.base;

import java.time.Instant;

public interface DomainEvent{
    Instant occurredOn();

}
