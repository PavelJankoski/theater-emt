package mk.ukim.finki.sharedkernel.infra.eventlog;

import mk.ukim.finki.sharedkernel.domain.base.DomainEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class DomainEventLogAppender {
    private final DomainEventLogService domainEventLogService;

    public DomainEventLogAppender(DomainEventLogService domainEventLogService) {
        this.domainEventLogService = domainEventLogService;
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onDomainEvent(DomainEvent domainEvent){
        domainEventLogService.append(domainEvent);
    }
}
