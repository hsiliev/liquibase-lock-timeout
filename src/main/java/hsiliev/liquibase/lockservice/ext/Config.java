package hsiliev.liquibase.lockservice.ext;

public class Config {
    private static final long DEFAULT_MAX_LOCK_TIMEOUT_MINUTES = 30;

    private static Config instance = new Config();

    public static Config getInstance() {
        return instance;
    }

    private Config() {
    }

    public long maxLockTimeoutMinutes() {
        try {
            return Long.parseLong(System.getProperty("liquibase.ext.lock.maxtimeoutminutes"));
        } catch (NumberFormatException e) {
            return DEFAULT_MAX_LOCK_TIMEOUT_MINUTES;
        }
    }
}
