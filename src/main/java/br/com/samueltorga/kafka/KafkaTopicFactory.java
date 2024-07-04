package br.com.samueltorga.kafka;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Requires;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsOptions;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.concurrent.TimeUnit;

@Requires(bean = AdminClient.class)
@Factory
public class KafkaTopicFactory {

    @Bean
    CreateTopicsOptions options() {
        return new CreateTopicsOptions()
                .timeoutMs((int) TimeUnit.SECONDS.toMillis(5))
                .validateOnly(false)
                .retryOnQuotaViolation(false);
    }

    @Bean
    NewTopic recentChangesTopic() {
        return new NewTopic("recent-changes", 6, (short) 3);
    }

}
