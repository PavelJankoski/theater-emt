package mk.ukim.finki.sharedkernel.domain.base;


import mk.ukim.finki.sharedkernel.infra.eventlog.StoredDomainEvent;

import java.util.List;

public interface RemoteEventLog {

    List<StoredDomainEvent> events();
}
