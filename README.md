# Zimara Backend

This is the backend companion of the Zimara project.

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=ZimaraIO_zimara-backend&metric=coverage)](https://sonarcloud.io/dashboard?id=ZimaraIO_zimara-backend)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ZimaraIO_zimara-backend&metric=alert_status)](https://sonarcloud.io/dashboard?id=ZimaraIO_zimara-backend)

## Building

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

### Requirements

You have to install in your machine

* Maven >= 3.6
* JDK >= 11

### Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
mvn compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

### Packaging and running the application

The application can be packaged using:

```shell script
mvn package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory. Be aware that it’s not an _über-jar_ as
the dependencies are copied into the `target/quarkus-app/lib/` directory.

If you want to build an _über-jar_, execute the following command:

```shell script
mvn package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

### Creating a native executable

You can create a native executable using:

```shell script
mvn package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
mvn package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/code-with-quarkus-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html
.

## Documentation

Documentation is generated using [LeafDoc](https://github.com/Leaflet/Leafdoc).

```shell script
npm install
npm test
```
