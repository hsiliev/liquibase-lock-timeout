package com.hsiliev.liquibase.lockservice.ext;

public class Config {
    public static final long DEFAULT_MAX_LOCK_TIMEOUT_MINUTES = 30;
    public static final String MAX_LOCK_TIMEOUT_PROPERTY = "liquibase.ext.changelogLockTimeoutInMinutes";

    private static final Config instance = new Config();

    public static Config getInstance() {
        return instance;
    }

    private Config() {
    }

    public long maxLockTimeoutMinutes() {
        try {
            return Long.parseLong(System.getProperty(MAX_LOCK_TIMEOUT_PROPERTY));
        } catch (NumberFormatException e) {
            return DEFAULT_MAX_LOCK_TIMEOUT_MINUTES;
        }
    }
}
