package hsiliev.liquibase.lockservice.ext;

import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.exception.LockException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LockServiceExtTest {

  private static final String CHANGE_LOG_FILE = "test-changelog.sql";
  private Liquibase liquibase;
  private Connection connection;

  @BeforeEach
  void setup() throws SQLException, LiquibaseException {
    connection = DriverManager.getConnection(
        "jdbc:h2:mem:testdb;MODE=Oracle;DEFAULT_NULL_ORDERING=HIGH",
        "sa",
        ""
    );
    ResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor();
    System.setProperty("liquibase.changelogLockWaitTimeInMinutes", "1");
    System.setProperty("liquibase.ext.changelogLockTimeoutInMinutes", "2");
    liquibase = new Liquibase(CHANGE_LOG_FILE, resourceAccessor, new JdbcConnection(connection));
    liquibase.update(new Contexts());
  }

  @AfterEach
  void cleanup() throws SQLException, LiquibaseException {
    liquibase.close();
    connection.close();
  }

  @Test
  void liquibase_withNewLock_throws() throws SQLException {
    createChangeLogLock(connection, "CURRENT_TIMESTAMP()");

    assertThrows(LockException.class, () -> liquibase.update(new Contexts()));
  }

  @Test
  void liquibase_withOldLock_succeeds() throws SQLException, LiquibaseException {
    createChangeLogLock(connection, "DATEADD(MINUTE, -3, CURRENT_TIMESTAMP())");

    liquibase.update(new Contexts());
  }

  private void createChangeLogLock(Connection connection, String timestamp) throws SQLException {
    Statement statement = connection.createStatement();
    statement.executeUpdate("UPDATE DATABASECHANGELOGLOCK\n" +
                                "SET locked=true,\n" +
                                "    lockgranted=" + timestamp + ",\n" +
                                "    lockedby=null\n" +
                                "WHERE ID = '1';");
    connection.commit();
  }
}
