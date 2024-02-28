FROM debian:bullseye-slim
RUN apt update && apt install -y openjdk-17-jre-headless && rm -rf /var/lib/apt/lists/*
RUN useradd -ms /bin/bash appuser
USER appuser
WORKDIR /app
COPY target/somafm-recentlib-functional-tests-fat-tests.jar .
CMD ["java", "-jar", "somafm-recentlib-functional-tests-fat-tests.jar"]
