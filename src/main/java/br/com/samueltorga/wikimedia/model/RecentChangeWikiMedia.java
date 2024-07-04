package br.com.samueltorga.wikimedia.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;

import java.net.URL;

@Builder
@Serdeable
public record RecentChangeWikiMedia(
    @JsonProperty("$schema")
    String schema,
    Meta meta,
    long id,
    String type,
    int namespace,
    String title,
    @JsonProperty("title_url")
    URL titleUrl,
    String comment,
    long timestamp,
    String user,
    boolean bot,
    @JsonProperty("notify_url")
    URL notifyUrl,
    boolean minor,
    boolean patrolled,
    Length length,
    Revision revision,
    @JsonProperty("server_url")
    URL serverUrl,
    @JsonProperty("server_name")
    String serverName,
    @JsonProperty("server_script_path")
    String serverScriptPath,
    String wiki,
    @JsonProperty("parsedcomment")
    String parsedComment
) {
}
