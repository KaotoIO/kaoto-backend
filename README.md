# Zimara Backend

This is the backend companion of the Zimara project.

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=ZimaraIO_zimara-backend&metric=coverage)](https://sonarcloud.io/dashboard?id=ZimaraIO_zimara-backend)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ZimaraIO_zimara-backend&metric=alert_status)](https://sonarcloud.io/dashboard?id=ZimaraIO_zimara-backend)

## Using Zimara

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

### Requirements

You have to install in your machine

* Maven >= 3.6
* JDK >= 17

### Running the application in dev mode

First you need to build and install the different maven modules.

```shell script
mvn install
```
Then you can run your application in dev mode that enables live coding using:

```shell script
mvn quarkus:dev -pl api
```

Your app is now deployed on `localhost:8080` and youc an check the swagger API on `http://localhost:8080/q/swagger-ui/`.

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

### Packaging and running the application

The application can be packaged using:

```shell script
mvn install
```

It produces the `quarkus-run.jar` file in the `api/target/quarkus-app/` directory. Be aware that it’s not an _über-jar_ as
the dependencies are copied into the `api/target/quarkus-app/lib/` directory.

If you want to build an _über-jar_, execute the following command:

```shell script
mvn install -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar api/target/quarkus-app/quarkus-run.jar`.

### Creating a native executable

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

## Documentation

Documentation is generated using [LeafDoc](https://github.com/Leaflet/Leafdoc).

```shell script
npm install
npm test
```
Published documentation is on https://zimaraio.github.io/zimara-backend/
