FROM amazoncorretto:11

COPY build/libs/happenings-*-all.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
