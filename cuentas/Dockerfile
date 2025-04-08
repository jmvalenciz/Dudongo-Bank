FROM eclipse-temurin:21-jdk-ubi9-minimal AS build

WORKDIR /app

COPY pom.xml mvnw ./
COPY .mvn .mvn

RUN ./mvnw dependency:go-offline

COPY src src
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre-ubi9-minimal AS runtime

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

## CA Certificates
# COPY my-cert.pem /usr/local/share/ca-certificates/my-cert.crt
# RUN update-ca-certificates && \
#     keytool -importcert -trustcacerts -keystore /etc/ssl/certs/java/cacerts \
#     -storepass changeit -noprompt -alias mycert -file /usr/local/share/ca-certificates/my-cert.crt

EXPOSE 3000
CMD ["java", "-jar", "app.jar"]

