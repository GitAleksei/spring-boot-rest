FROM adoptopenjdk/openjdk11
EXPOSE 8080
ADD target/spring_boot_rest-0.0.1-SNAPSHOT.jar spring_boot_rest.jar
ENTRYPOINT ["java", "-jar", "spring_boot_rest.jar"]