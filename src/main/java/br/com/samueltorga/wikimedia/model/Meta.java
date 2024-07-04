package br.com.samueltorga.wikimedia.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;

import java.net.URL;

@Serdeable
public record Meta(
    URL uri,
    @JsonProperty("request_id")
    String requestId,
    String id,
    String dt,
    String domain,
    String stream,
    String topic,
    int partition,
    long offset
) {
}
