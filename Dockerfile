FROM openjdk:8-jdk-alpine
MAINTAINER srodriguez2104@gmail.com
EXPOSE 8080
ARG JAR_FILE=target/coupon-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} /coupon-api.jar
ENTRYPOINT ["java","-jar","/cupon-api.jar"]