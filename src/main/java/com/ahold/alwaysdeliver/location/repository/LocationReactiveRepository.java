package com.ahold.alwaysdeliver.location.repository;

import com.ahold.alwaysdeliver.location.payload.Location;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationReactiveRepository extends ReactiveMongoRepository<Location, ObjectId> {

}
