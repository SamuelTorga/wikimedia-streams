package br.com.samueltorga.repository;

import br.com.samueltorga.repository.entity.RecentChange;
import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import org.bson.types.ObjectId;

@MongoRepository
public interface RecentChangesRepository extends CrudRepository<RecentChange, ObjectId> {
}
