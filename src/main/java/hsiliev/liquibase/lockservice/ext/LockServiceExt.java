package hsiliev.liquibase.lockservice.ext;

import liquibase.Scope;
import liquibase.exception.DatabaseException;
import liquibase.exception.LockException;
import liquibase.executor.Executor;
import liquibase.executor.ExecutorService;
import liquibase.lockservice.StandardLockService;
import liquibase.logging.Logger;
import liquibase.statement.core.SelectFromDatabaseChangeLogLockStatement;

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
    String dbName = database.getDatabaseChangeLogLockTableName();
    LOG.info("Checking lock date for " + dbName);

    try {
      if (this.hasDatabaseChangeLogLockTable()) {
        Executor executor = Scope.getCurrentScope().getSingleton(ExecutorService.class).getExecutor(
            "jdbc", database
        );
        Boolean locked = executor.queryForObject(
            new SelectFromDatabaseChangeLogLockStatement("LOCKED"), Boolean.class
        );
        if (locked == Boolean.TRUE) {
          Date lockedDate = executor.queryForObject(
              new SelectFromDatabaseChangeLogLockStatement("LOCKEDGRANTED"), Date.class
          );
          if (lockedDate != null) {
            long minutesSinceLock = Duration.between(lockedDate.toInstant(), Instant.now()).toMinutes();
            LOG.info("Lock on " + dbName + " created at " + lockedDate + ", " + minutesSinceLock + " minutes from now");
            if (minutesSinceLock > 30) {
              LOG.warning("Releasing lock on " + dbName + " as max lock time is " +
                              MAX_LOCK_TIMEOUT_MINUTES + " minutes");
              releaseLock();
            }
          } else {
            LOG.warning("LOCKEDGRANTED field is missing for locked database " + dbName);
          }
        }
      }
    } catch (DatabaseException e) {
      LOG.severe("Failed to check lock date on " + dbName, e);
    }

    super.waitForLock();
  }
}
