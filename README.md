# Kaoto Backend

This is the backend companion of the Kaoto project.

![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/kaotoio/kaoto-backend?include_prereleases)
![Docker Image Version (latest by date)](https://img.shields.io/docker/v/kaotoio/backend?label=docker%20version&sort=date)
![Docker Pulls](https://img.shields.io/docker/pulls/kaotoio/backend)
![Docker Image Size (tag)](https://img.shields.io/docker/image-size/kaotoio/backend/latest?label=docker%20image)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=KaotoIO_kaoto-backend&metric=alert_status)](https://sonarcloud.io/dashboard?id=KaotoIO_kaoto-backend)
[![codecov](https://codecov.io/gh/KaotoIO/kaoto-backend/branch/main/graph/badge.svg?token=7RADJHV7HT)](https://codecov.io/gh/KaotoIO/kaoto-backend)
[![DeepSource](https://deepsource.io/gh/KaotoIO/kaoto-backend.svg/?label=active+issues&show_trend=true&token=AD8N4e26NKVNN6S3mM85hwe-)](https://deepsource.io/gh/KaotoIO/kaoto-backend/?ref=repository-badge)


![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/KaotoIO/kaoto-backend)
![GitHub commit activity](https://img.shields.io/github/commit-activity/m/kaotoio/kaoto-backend)
![GitHub contributors](https://img.shields.io/github/contributors/kaotoio/kaoto-backend)
![GitHub](https://img.shields.io/github/license/kaotoio/kaoto-backend)

![Twitter Follow](https://img.shields.io/twitter/follow/kaotoio?style=social)
![YouTube Channel Subscribers](https://img.shields.io/youtube/channel/subscribers/UCcWUAnL5sBYVFen0RMxbZ3A?style=social)

## Using Kaoto

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

### Running as a docker container

There is a nightly dockerized container for the Kaoto backend. 
You can run it with the following command:

`docker run --rm -d -p 8080:8080 kaotoio/backend`

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

Your app is now deployed on `localhost:8080` and you can check the swagger API on `http://localhost:8080/q/swagger-ui/`.

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

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
npm test
```
