<!--
SPDX-FileCopyrightText: 2023 Alliander N.V.

SPDX-License-Identifier: Apache-2.0
-->

[![Build Project Github Action Status](https://img.shields.io/github/workflow/status/com-pas/compas-sitipe-service/Build%20Project?logo=GitHub)](https://github.com/com-pas/compas-sitipe-service/actions?query=workflow%3A"Build+Project")
[![REUSE status](https://api.reuse.software/badge/github.com/com-pas/compas-sitipe-service)](https://api.reuse.software/info/github.com/com-pas/compas-sitipe-service)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com-pas_compas-sitipe-service&metric=alert_status)](https://sonarcloud.io/dashboard?id=com-pas_compas-sitipe-service)
[![CII Best Practices](https://bestpractices.coreinfrastructure.org/projects/5925/badge)](https://bestpractices.coreinfrastructure.org/projects/5925)
[![Slack](https://raw.githubusercontent.com/com-pas/compas-architecture/master/public/LFEnergy-slack.svg)](http://lfenergy.slack.com/)

# CoMPAS Sitipe Service

### Local Development
You can start the mssql database by running one of the following commands:

#### Mac M1
```
docker-compose --env-file docker/.env -f docker/docker-compose-m1.yml up -d --build
```

#### AMD64
```
docker-compose --env-file docker/.env -f docker/docker-compose-amd64.yml up -d --build
```

Quarkus can be started in dev mode:
```
./mvnw -DskipTests=true  package io.quarkus:quarkus-maven-plugin::dev
```