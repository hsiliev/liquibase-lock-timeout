# Liquibase lock timeout
Liquibase LockService that implements maximum lock time policy.

## Usage:
Include the generated jar in your project dependencies, it will override Liquibase default locking.

## Configuration
Java system property `liquibase.ext.lock.maxtimeoutminutes` can be used to change the default 30 minutes timeout

## Credits:
* https://github.com/oridool/liquibase-locking
* https://github.com/liquibase/liquibase-nochangeloglock
