FROM openjdk:17

COPY . /tmp

WORKDIR /tmp

CMD ["java", "-jar", "JAR/main.jar"]