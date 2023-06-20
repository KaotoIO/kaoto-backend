# Kaoto Backend

This is the backend companion of the Kaoto project.

![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/kaotoio/kaoto-backend?include_prereleases)
![GitHub](https://img.shields.io/github/license/kaotoio/kaoto-backend)

![Docker Pulls](https://img.shields.io/docker/pulls/kaotoio/frontend)
[![Docker generation](https://github.com/KaotoIO/kaoto-backend/actions/workflows/generate-docker-image.yml/badge.svg)](https://github.com/KaotoIO/kaoto-backend/actions/workflows/generate-docker-image.yml)

![GitHub contributors](https://img.shields.io/github/contributors/kaotoio/kaoto-backend)
![GitHub commit activity](https://img.shields.io/github/commit-activity/m/kaotoio/kaoto-backend)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/KaotoIO/kaoto-backend)
[![codecov](https://codecov.io/gh/KaotoIO/kaoto-backend/branch/main/graph/badge.svg?token=7RADJHV7HT)](https://codecov.io/gh/KaotoIO/kaoto-backend)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=KaotoIO_kaoto-backend&metric=alert_status)](https://sonarcloud.io/dashboard?id=KaotoIO_kaoto-backend)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=KaotoIO_kaoto-backend&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=KaotoIO_kaoto-backend)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=KaotoIO_kaoto-backend&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=KaotoIO_kaoto-backend)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=KaotoIO_kaoto-backend&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=KaotoIO_kaoto-backend)

![Twitter Follow](https://img.shields.io/twitter/follow/kaotoio?style=social)
![YouTube Channel Subscribers](https://img.shields.io/youtube/channel/subscribers/UCcWUAnL5sBYVFen0RMxbZ3A?style=social)

## Using Kaoto

This is the [API companion](https://kaotoio.github.io/kaoto-backend/api/index.html) for the Kaoto frontend. It is designed as an [hexagonal architecture](https://alistair.cockburn.us/hexagonal-architecture/), decoupled and modularized to be able to easily [add your own DSL](https://kaoto.io/docs/add-dsl/), and with [maintainability in mind](https://kaoto.io/docs/architecture/). 

This project uses Quarkus, the Supersonic Subatomic Java Framework. If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

### Running as a docker container

There is a nightly dockerized container for the Kaoto backend. 
You can run it with the following command:

`docker run --rm -d -p 8081:8081 kaotoio/backend`

If you want to use it with Kaoto-ui, don't forget to set CORS origins via QUARKUS_HTTP_CORS_ORIGINS env, e.g.:

`docker run --rm -d -p 8081:8081 -e QUARKUS_HTTP_CORS_ORIGINS='http://localhost:1337' kaotoio/backend`

### Using OpenTelemetry tracing

Kaoto-backend provides OpenTelemetry tracing. By default, OpenTelemetry SDK Autoconfigure is disabled.
It can be enabled by `quarkus.otel.sdk.disabled=false` 
Note that after that, the OTLP Exporter (e.g. Jaeger) is expected on `http://localhost:4317/` but the endpoint can be overridden via `quarkus.otel.exporter.otlp.traces.endpoint` configuration property.
For more information and all configuration properties, see [Quarkus OpenTelemetry guide](https://quarkus.io/guides/opentelemetry)

### CORS

[CORS filter](https://quarkus.io/guides/http-reference#cors-filter) is enabled by default.
For proper functionality with the Kaoto-ui, it is necessary to set `quarkus.http.cors.origins` configuration property with Kaoto-ui URL(s).
For more information and all configuration properties, see [Quarkus HTTP Reference](https://quarkus.io/guides/http-reference#quarkus-vertx-http-config-group-cors-cors-config_configuration)

Note: When you run Kaoto-backend in the dev mode, all origins are accepted.

## Developing Kaoto

Developer documentation is on https://kaotoio.github.io/kaoto-backend/

The API static swagger documentation is on  https://kaotoio.github.io/kaoto-backend/api/index.html

### Requirements

You have to install in your machine

* Maven >= 3.6
* JDK >= 17

### Building 

First you need to build and install the different maven modules.

```shell script
mvn install
```

### Running the dev mode

Then you can run your application in dev mode that enables live coding using:

```shell script
mvn quarkus:dev -pl api
```

Your app is now deployed on `localhost:8081` and you can check the swagger 
API on `http://localhost:8081/q/swagger-ui/`.

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev 
> mode only at http://localhost:8081/q/dev/.

### Packaging and Running

Kaoto can be packaged using:

```shell script
mvn install
```

It produces the `quarkus-run.jar` file in the `api/target/quarkus-app/` directory. Be aware that it’s not an _über-jar_ as
the dependencies are copied into the `api/target/quarkus-app/lib/` directory.

If you want to build an _über-jar_, execute the following command:

```shell script
mvn install -Dquarkus.package.type=uber-jar
```

Kaoto backend is now runnable using `java -jar api/target/quarkus-app/quarkus-run.jar`.

#### Creating a native executable

You can create a native executable using:

```shell script
mvn install -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
mvn install -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `.api/target/code-with-quarkus-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html
.

#### Building the Documentation

Documentation is generated using [LeafDoc](https://github.com/Leaflet/Leafdoc).

```shell script
npm install
npm run docs
```
