package br.com.samueltorga.wikimedia;

import br.com.samueltorga.kafka.RecentChangesProducer;
import br.com.samueltorga.wikimedia.model.RecentChangeWikiMedia;
import io.micronaut.context.annotation.Value;
import io.micronaut.context.event.ApplicationEvent;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.ShutdownEvent;
import io.micronaut.discovery.event.ServiceReadyEvent;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.serde.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import okhttp3.sse.EventSources;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.concurrent.ExecutorService;

@Slf4j
@Singleton
@RequiredArgsConstructor(onConstructor_ = @__({@Inject}))
public class WikimediaStreamService implements ApplicationEventListener<ApplicationEvent> {

    private final OkHttpClient httpClient;

    private final RecentChangesProducer recentChangesProducer;

    private final ObjectMapper mapper;

    private EventSource eventSource;

    @Value("${wikimedia.stream.url}")
    private String wikimediaStreamUrl;

    @Value("${wikimedia.stream.enabled}")
    private boolean wikimediaStreamEnabled;

    @Value("${wikimedia.stream.path.recent-changes}")
    private String recentChangesPath;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (!wikimediaStreamEnabled) {
            return;
        }
        if (event instanceof ServiceReadyEvent) {
            log.info("Starting Wikimedia stream");

            try {
                URL url = UriBuilder.of(wikimediaStreamUrl).path(recentChangesPath).build().toURL();
                Request request = new Request.Builder().url(url).build();

                eventSource = EventSources.createFactory(httpClient).newEventSource(request, new EventSourceListener() {
                    @Override
                    public void onOpen(@NotNull EventSource eventSource, @NotNull Response response) {
                        log.debug("EventSource opened: {}", eventSource);
                    }

                    @Override
                    public void onClosed(@NotNull EventSource eventSource) {
                        log.debug("EventSource closed: {}", eventSource);
                    }

                    @Override
                    public void onFailure(@NotNull EventSource eventSource, Throwable t, Response response) {
                        log.error("Error on stream", t);
                    }

                    @Override
                    public void onEvent(@NotNull EventSource eventSource, String id, String type, @NotNull String data) {
                        try {
                            RecentChangeWikiMedia recentChangeWikiMedia = mapper.readValue(data, RecentChangeWikiMedia.class);
                            recentChangesProducer.send(recentChangeWikiMedia);
                        } catch (Exception e) {
                            log.error("Error on parsing line: {}", data, e);
                        }
                    }
                });
            } catch (Exception e) {
                log.error("Error on starting Wikimedia stream", e);
            }
        } else if (event instanceof ShutdownEvent && eventSource != null) {
            log.info("Closing Wikimedia stream");
            eventSource.cancel();
            try (ExecutorService executorService = httpClient.dispatcher().executorService()) {
                executorService.shutdown();
                log.info("Wikimedia stream closed");
            } catch (Exception e) {
                log.error("Error on closing Wikimedia stream", e);
            }
        }

    }


}
