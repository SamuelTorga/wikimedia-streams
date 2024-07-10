package br.com.samueltorga.kafka;

import br.com.samueltorga.opensearch.WikimediaOpenSearchClient;
import br.com.samueltorga.wikimedia.model.RecentChangeWikiMedia;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@KafkaListener(groupId = "recent-changes-consumer-opensearch")
@RequiredArgsConstructor(onConstructor_ = @__(@Inject))
public class RecentChangesConsumerOpenSearch {

    private final WikimediaOpenSearchClient recentChangesService;

    @Topic("recent-changes")
    public void readRecentChanges(RecentChangeWikiMedia recentChangeWikiMedia, long offset, String topic, int partition) {
        log.debug("Consuming message from topic: {}, partition: {}, offset: {}", topic, partition, offset);
        recentChangesService.saveRecentChange(recentChangeWikiMedia);
    }

}
