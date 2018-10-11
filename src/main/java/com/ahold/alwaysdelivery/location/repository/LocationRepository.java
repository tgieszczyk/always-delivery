package com.ahold.alwaysdelivery.location.repository;

import com.ahold.alwaysdelivery.location.payload.Location;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends ReactiveMongoRepository<Location, ObjectId> {

}
