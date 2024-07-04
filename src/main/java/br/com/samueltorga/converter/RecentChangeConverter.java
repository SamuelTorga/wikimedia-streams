package br.com.samueltorga.converter;

import br.com.samueltorga.repository.entity.RecentChange;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecentChangeConverter {

    RecentChangeConverter INSTANCE = Mappers.getMapper(RecentChangeConverter.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "domain", source = "meta.domain")
    RecentChange toRecentChange(br.com.samueltorga.wikimedia.model.RecentChangeWikiMedia recentChangeWikiMedia);

}
