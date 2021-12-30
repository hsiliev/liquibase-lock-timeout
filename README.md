# Liquibase lock timeout
Liquibase LockService that implements maximum lock time policy.

[![Java CI with Gradle](https://github.com/hsiliev/liquibase-lock-timeout/actions/workflows/gradle.yml/badge.svg)](https://github.com/hsiliev/liquibase-lock-timeout/actions/workflows/gradle.yml)
[![Gradle Package](https://github.com/hsiliev/liquibase-lock-timeout/actions/workflows/gradle-publish.yml/badge.svg)](https://github.com/hsiliev/liquibase-lock-timeout/actions/workflows/gradle-publish.yml)
[![codecov](https://codecov.io/gh/hsiliev/liquibase-lock-timeout/branch/main/graph/badge.svg?token=Y9TJ86AVTF)](https://codecov.io/gh/hsiliev/liquibase-lock-timeout)
[![CodeQL](https://github.com/hsiliev/liquibase-lock-timeout/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/hsiliev/liquibase-lock-timeout/actions/workflows/codeql-analysis.yml)

## Usage:
Include the generated jar in your project dependencies, it will override Liquibase default locking.

## Configuration
Java system property `liquibase.ext.lock.maxtimeoutminutes` can be used to change the default 30 minutes timeout

## Publishing

Regeneration of GPG key:
```shell
# Provide name, email and GPG signing password
gpg --gen-key

# Obtain GPG signing key
gpg --list-keys
# Obtain GPG secret key in armored ascii format
gpg --armor --export-secret-keys hsiliev@gmail.com | awk 'NR == 1 { print "GPG_SIGNING_KEY=" } 1' ORS='\\n'
```


## Credits:
* https://github.com/oridool/liquibase-locking
* https://github.com/liquibase/liquibase-nochangeloglock
* https://www.baeldung.com/quarkus-extension-java
