package br.com.samueltorga.kafka;

import br.com.samueltorga.wikimedia.model.RecentChangeWikiMedia;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient(
        id = "recent-changes-producer",
        acks = KafkaClient.Acknowledge.ALL
)
public interface RecentChangesProducer {

    @Topic("recent-changes")
    void send(RecentChangeWikiMedia value);

}
