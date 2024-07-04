package br.com.samueltorga.kafka;

import br.com.samueltorga.service.RecentChangesService;
import br.com.samueltorga.wikimedia.model.RecentChangeWikiMedia;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@KafkaListener(groupId = "recent-changes-consumer", threads = 6)
@RequiredArgsConstructor(onConstructor_ = @__(@Inject))
public class RecentChangesConsumer {

    private final RecentChangesService recentChangesService;

    @Topic("recent-changes")
    public void readRecentChanges(RecentChangeWikiMedia recentChangeWikiMedia) {
        recentChangesService.save(recentChangeWikiMedia);
    }

}
