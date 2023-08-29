# Build
#---------------------------------------------------------
FROM amazoncorretto:20-alpine-jdk as build
WORKDIR /tmp/echo
COPY . .
RUN ./gradlew :bootJar

#---------------------------------------------------------

# Final
#---------------------------------------------------------
FROM amazoncorretto:20-alpine-jdk

RUN adduser -S echo
USER echo
WORKDIR /home/echo

COPY --from=build /tmp/echo/build/libs/echo-core.jar ./echo-core.jar
CMD ["java","-jar","echo-core.jar","echo_backend"]
#---------------------------------------------------------