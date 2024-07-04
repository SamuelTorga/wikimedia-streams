package br.com.samueltorga.service;

import br.com.samueltorga.converter.RecentChangeConverter;
import br.com.samueltorga.repository.RecentChangesRepository;
import br.com.samueltorga.wikimedia.model.RecentChangeWikiMedia;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor(onConstructor_ = @__(@Inject))
public class RecentChangesService {

    private final RecentChangesRepository repository;

    public void save(RecentChangeWikiMedia recentChangeWikiMedia) {
        repository.save(RecentChangeConverter.INSTANCE.toRecentChange(recentChangeWikiMedia));
    }

}
