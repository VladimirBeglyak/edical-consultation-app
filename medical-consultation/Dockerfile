FROM gradle:latest AS build
COPY --chown=gradle:gradle .. /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM joengenduvel/jre17
EXPOSE 8080
RUN mkdir /app
ARG RUN_DIR=/home/gradle/src/build/libs/
COPY --from=build ${RUN_DIR}*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
