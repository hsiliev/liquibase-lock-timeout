package hsiliev.liquibase.lockservice.ext;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigTest {

  @Test
  void maxLockTimeoutMinutes_returnsDefaultValue() {
    assertEquals(Config.DEFAULT_MAX_LOCK_TIMEOUT_MINUTES,
                 Config.getInstance().maxLockTimeoutMinutes());
  }

  @Test
  void maxLockTimeoutMinutes_returnsConfiguredValue() {
    final long EXTENDED_LOCK_TIME = 1000;
    System.setProperty(Config.MAX_LOCK_TIMEOUT_PROPERTY, "" + EXTENDED_LOCK_TIME);
    assertEquals(EXTENDED_LOCK_TIME, Config.getInstance().maxLockTimeoutMinutes());
  }

  @Test
  void maxLockTimeoutMinutes_withBadConfig_returnsDefaultValue() {
    System.setProperty(Config.MAX_LOCK_TIMEOUT_PROPERTY, "bad_value11");
    assertEquals(Config.DEFAULT_MAX_LOCK_TIMEOUT_MINUTES, Config.getInstance().maxLockTimeoutMinutes());
  }

}
