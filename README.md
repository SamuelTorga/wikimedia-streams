# Wikimedia Streams

Repository for Wikimedia Streams project.

In this project we are consuming Wikimedia changes stream, posting on a Kafka Topic and storing them in a MongoDB
database after consuming the messages using a Kafka Consumer.

### Technologies used:

- Java 21
- Micronaut 4.5.0
    - Micronaut Kafka
    - Micronaut Data MongoDB
- Kafka
- MongoDB
- HTTP SSE (Server-Sent Events)
    - OkHttp3
- Docker
    - Docker Compose
- OpenSearch