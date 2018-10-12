package com.ahold.allwaysdelivery.locker.repository;

import com.ahold.allwaysdelivery.locker.payload.Locker;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * @author tgieszczyk@spyro-soft.com
 *
 */
@Repository
public interface LockerRepository extends MongoRepository<Locker, ObjectId> {
	@Query(value = "{'key' : ?0, 'createdAt' : { $gte: ?1}}", exists = true)
	boolean isKeyExists(String key, LocalDateTime validFrom);

	@Query(value = "{$or: [ {$and: [{'key' : ?0}, {'nodeId': ?1}]}, {'createdAt' : { $lt: ?2}} ] }", delete = true)
	Long releaseLocker(String key, String nodeId, LocalDateTime validFrom);

	Locker findByKeyOrderByCreatedAtDesc(String key);
}
