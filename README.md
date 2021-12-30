# Liquibase lock timeout
Liquibase LockService that implements maximum lock time policy.

[![Java CI with Gradle](https://github.com/hsiliev/liquibase-lock-timeout/actions/workflows/gradle.yml/badge.svg)](https://github.com/hsiliev/liquibase-lock-timeout/actions/workflows/gradle.yml)
[![Gradle Package](https://github.com/hsiliev/liquibase-lock-timeout/actions/workflows/gradle-publish.yml/badge.svg)](https://github.com/hsiliev/liquibase-lock-timeout/actions/workflows/gradle-publish.yml)
[![codecov](https://codecov.io/gh/hsiliev/liquibase-lock-timeout/branch/main/graph/badge.svg?token=Y9TJ86AVTF)](https://codecov.io/gh/hsiliev/liquibase-lock-timeout)
[![CodeQL](https://github.com/hsiliev/liquibase-lock-timeout/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/hsiliev/liquibase-lock-timeout/actions/workflows/codeql-analysis.yml)

## Usage
Include the generated jar in your project dependencies, it will override Liquibase default locking.

### Maven
```xml
<dependency>
  <groupId>com.hsiliev</groupId>
  <artifactId>liquibase-lock-timeout</artifactId>
  <version>0.5.5</version>
  <scope>runtime</scope>
</dependency>
```

### Gradle
```groovy
runtimeOnly 'com.hsiliev:liquibase-lock-timeout:0.5.5'
```

## Configuration
Java system property `liquibase.ext.lock.maxtimeoutminutes` can be used to change the default 30 minutes timeout

## Credits
* https://github.com/oridool/liquibase-locking
* https://github.com/liquibase/liquibase-nochangeloglock
* https://www.baeldung.com/quarkus-extension-java
