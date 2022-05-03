#FROM maven:3.6.3-jdk-11
#
#RUN mkdir /app
#
#WORKDIR /app
#
#COPY pom.xml /app/pom.xml
#
#RUN mvn -B dependency:resolve
#
#EXPOSE 8080