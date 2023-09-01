# Echo Backend

## How to Run
```shell
$ ./gradlew :bootRun --console=plain --args='echo_backend'
```

## Autorestart
Spring Boot Devtool is not working properly because of the modules. **It is only available for the Apps module**, but it's
possible to add a way of restarting manually:

```properties
# resources/META-INF/spring-devtools.properties

# This fixes the following error:
# Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'echoDataSource' defined in class path resource [xyz/proyectogshort/echo/shared/infrastructure/hibernate/EchoHibernateConfiguration.class]: Failed to instantiate [javax.sql.DataSource]: Illegal arguments to factory method 'echoDataSource'; args:
restart.include.modules=.*-plain\.jar
```

```groovy
/*
 *  "./gradlew restart" reloads all modules.
 *  Probably clean command it's needed before the next execution. 
 */
 
subprojects {
    tasks.register('restart', Copy) {
        from "$rootDir/src/$project.name/build/classes/"
        into "$rootDir/build/classes/"

        dependsOn(":$project.name:classes")
    }
}
```
[ℹ️ https://reflectoring.io/spring-boot-dev-tools/](https://reflectoring.io/spring-boot-dev-tools/)