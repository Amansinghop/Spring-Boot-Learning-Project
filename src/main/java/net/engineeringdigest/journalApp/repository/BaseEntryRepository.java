package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.BaseEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BaseEntryRepository extends MongoRepository<BaseEntry, String> {
}
