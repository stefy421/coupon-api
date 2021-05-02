FROM openjdk:8-jdk-alpine
MAINTAINER srodriguez2104@gmail.com
VOLUME /tmp
ARG JAR_FILE=target/coupon-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} coupon.jar
ENTRYPOINT ["java","-jar","/coupon.jar"]