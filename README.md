# Liquibase lock timeout
Liquibase LockService that implements maximum lock time policy.

[![Java CI with Gradle](https://github.com/hsiliev/liquibase-lock-timeout/actions/workflows/gradle.yml/badge.svg)](https://github.com/hsiliev/liquibase-lock-timeout/actions/workflows/gradle.yml)
[![Gradle Package](https://github.com/hsiliev/liquibase-lock-timeout/actions/workflows/gradle-publish.yml/badge.svg)](https://github.com/hsiliev/liquibase-lock-timeout/actions/workflows/gradle-publish.yml)
[![Coverage Status](https://coveralls.io/repos/github/hsiliev/liquibase-lock-timeout/badge.svg?branch=main)](https://coveralls.io/github/hsiliev/liquibase-lock-timeout?branch=main)

## Usage:
Include the generated jar in your project dependencies, it will override Liquibase default locking.

## Configuration
Java system property `liquibase.ext.lock.maxtimeoutminutes` can be used to change the default 30 minutes timeout

## Credits:
* https://github.com/oridool/liquibase-locking
* https://github.com/liquibase/liquibase-nochangeloglock
* https://www.baeldung.com/quarkus-extension-java
