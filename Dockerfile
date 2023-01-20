

FROM eclipse-temurin:17-jre-alpine

EXPOSE 8080

COPY DevOpsPipeline.jar /src/
WORKDIR /src/

ENTRYPOINT ["java", "-jar", "DevOpsPipeline.jar"]
