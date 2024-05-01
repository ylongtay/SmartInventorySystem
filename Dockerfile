# The base image. 
FROM eclipse-temurin:21-jdk-alpine

# The work directory. 
WORKDIR /

# The environment port to expose. 
ENV PORT=9090

# The JAR file path. 
ARG JAR_FILE=*.jar

# Copy the JAR file from the build context into the Docker image. 
COPY target/${JAR_FILE} application.jar
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./

#COPY src ./src

#CMD apt-get update -y

# Set the default command to run the Java application. 
#ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/application.jar"]
ENTRYPOINT ["java","-jar","/application.jar"]
#RUN ./mvnw install -DskipTests
#CMD ["./mvnw", "spring-boot:run"]
