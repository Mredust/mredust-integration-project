FROM openjdk:8

COPY ./target/mredust-user-system-0.0.1.jar app.jar

EXPOSE 4091

#ENTRYPOINT ["java","-jar","app.jar","--spring.profiles.active=prod"]
ENTRYPOINT ["java","-jar","app.jar","--spring.profiles.active=test"]
