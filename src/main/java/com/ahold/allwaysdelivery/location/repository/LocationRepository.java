package com.ahold.allwaysdelivery.location.repository;

import com.ahold.allwaysdelivery.location.payload.Location;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends MongoRepository<Location, ObjectId> {

}
