package br.com.samueltorga.repository.entity;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.time.Instant;

@MappedEntity("recent_changes")
@Builder
@AllArgsConstructor
@Getter
@Setter
public class RecentChange {

    @Id
    @GeneratedValue
    private ObjectId id;

    @NonNull
    @NotBlank
    private String title;

    @NonNull
    @Builder.Default
    @DateCreated
    private Instant createdAt = Instant.now();

    @NonNull
    @NotBlank
    private String domain;

    @NonNull
    @NotBlank
    private String type;

    @NonNull
    @NotBlank
    private String user;

}
