package com.ahold.allwaysdelivery.ride.repository;

import com.ahold.allwaysdelivery.ride.payload.Ride;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RideRepository extends MongoRepository<Ride, ObjectId> {
}