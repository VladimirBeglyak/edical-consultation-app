FROM joengenduvel/jre17

WORKDIR /app

COPY medical-consultation/build/libs .

CMD ["java", "-jar", "medical-consultation-0.0.1-SNAPSHOT.jar"]
