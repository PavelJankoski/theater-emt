package mk.ukim.finki.sharedkernel.infra.eventlog;

import mk.ukim.finki.sharedkernel.domain.base.RemoteEventLog;

public interface RemoteEventLogService {
    String source();

    RemoteEventLog currentLog(long lastProcessedId);

}
