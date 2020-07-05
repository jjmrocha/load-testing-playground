# Build image
FROM golang:1.14.4 AS build

ENV BUILD_HOME "/build"
RUN mkdir $BUILD_HOME
WORKDIR $BUILD_HOME

COPY fiber.go go.mod go.sum ./
COPY app app

RUN go build fiber.go

# Final image
FROM gcr.io/distroless/base-debian10
COPY --from=build /build/fiber /
EXPOSE 8080
CMD ["/fiber"]