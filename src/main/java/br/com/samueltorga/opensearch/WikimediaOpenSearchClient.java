package br.com.samueltorga.opensearch;

import br.com.samueltorga.wikimedia.model.RecentChangeWikiMedia;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opensearch.client.opensearch.OpenSearchAsyncClient;
import org.opensearch.client.opensearch.core.IndexRequest;

@Slf4j
@Singleton
@RequiredArgsConstructor(onConstructor_ = @__({@Inject}))
public class WikimediaOpenSearchClient {

    private final OpenSearchAsyncClient openSearchClient;

    public void saveRecentChange(RecentChangeWikiMedia recentChange) {
        try {
            IndexRequest<RecentChangeWikiMedia> indexRequest = createIndexRequest(recentChange);
            openSearchClient.index(indexRequest);
        } catch (Exception e) {
            log.error("Error saving recent change: {}", recentChange, e);
        }
    }

    private IndexRequest<RecentChangeWikiMedia> createIndexRequest(RecentChangeWikiMedia recentChange) {
        return new org.opensearch.client.opensearch.core.IndexRequest.Builder<RecentChangeWikiMedia>()
                .document(recentChange)
                .index("recent_changes")
                .id(recentChange.meta().id())
                .build();
    }

}
