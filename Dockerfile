# 빌드 단계
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /back
COPY . .
RUN ./gradlew clean build

# 실행 단계
FROM eclipse-temurin:21-jre
WORKDIR /back
COPY --from=builder /back/build/libs/weddy-0.0.1-SNAPSHOT.jar /back/weddy.jar
EXPOSE 8080
CMD ["java", "-Dspring.profiles.active=local", "-jar", "weddy.jar"]