# Build image
FROM maven:3-jdk-11 AS build

ENV BUILD_HOME "/build"
RUN mkdir $BUILD_HOME
WORKDIR $BUILD_HOME

COPY pom.xml .
COPY src src
RUN mvn clean package

# Final image
FROM openjdk:12-jdk-alpine

ENV APP_HOME "/system"
RUN mkdir $APP_HOME
WORKDIR $APP_HOME

COPY --from=build /build/target/apps-app.jar .

ARG UNAME=webapp
ARG UID=500
ARG GID=500
RUN addgroup --system --gid $GID $UNAME
RUN adduser --system --uid $UID --ingroup $UNAME --disabled-password --no-create-home $UNAME
RUN chown $UNAME:$UNAME -R $APP_HOME
USER $UNAME

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "apps-app.jar"]