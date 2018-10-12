package com.ahold.allwaysdelivery.mongobee.dao;

import com.ahold.allwaysdelivery.mongobee.changeset.ChangeEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tomasz Gieszczyk
 *
 */
@Repository
public interface ChangeEntryRepository extends MongoRepository<ChangeEntry, String> {

}
