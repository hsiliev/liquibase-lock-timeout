package hsiliev.liquibase.lockservice.ext;

import liquibase.Scope;
import liquibase.exception.LockException;
import liquibase.lockservice.DatabaseChangeLogLock;
import liquibase.lockservice.StandardLockService;
import liquibase.logging.Logger;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class LockServiceExt extends StandardLockService {

  private static final Logger LOG = Scope.getCurrentScope().getLog(LockServiceExt.class);
  private static final Long MAX_LOCK_TIMEOUT_MINUTES = Config.getInstance().maxLockTimeoutMinutes();

  @Override
  public int getPriority() {
    return 1000;
  }

  @Override
  public void waitForLock() throws LockException {
    String dbName = database.getDatabaseProductName();
    LOG.info("Checking lock granted date for " + dbName);

    DatabaseChangeLogLock[] locks = listLocks();
    if (locks.length > 0) {
      DatabaseChangeLogLock lock = locks[0];
      Date lockedGranted = lock.getLockGranted();
      long minutesSinceLock = Duration.between(lockedGranted.toInstant(), Instant.now()).toMinutes();
      LOG.info("Lock on " + dbName + " granted at " + lockedGranted + ", " + minutesSinceLock + " minutes from now");
      if (minutesSinceLock > MAX_LOCK_TIMEOUT_MINUTES) {
        LOG.warning("Releasing lock on " + dbName + " as max lock time is " +
                        MAX_LOCK_TIMEOUT_MINUTES + " minutes");
        releaseLock();
      }
    }

    super.waitForLock();
  }
}
