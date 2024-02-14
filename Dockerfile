FROM openjdk:17
EXPOSE 8095
ADD ./target/microservice-prices-0.0.1-SNAPSHOT.jar microservice-prices.jar
ENTRYPOINT ["java", "-jar", "microservice-prices.jar"]
