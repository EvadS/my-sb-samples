FROM openjdk:17-jdk-slim
WORKDIR /opt
ENV PORT 8080

ENV DB_NAME: demoDb
ENV DB_SERVER: localhost
ENV DB_PORT: 3306
ENV DB_USERNAME: root
ENV DB_PASSWORD: 31323334


EXPOSE 8080
COPY target/*.jar /opt/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar