package br.com.samueltorga.wikimedia.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record Length(
    int old,
    @JsonProperty("new")
    int newLength
) {
}
