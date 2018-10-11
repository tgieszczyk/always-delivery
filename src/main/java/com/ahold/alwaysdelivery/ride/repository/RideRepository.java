package com.ahold.alwaysdelivery.ride.repository;

import com.ahold.alwaysdelivery.ride.payload.Ride;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends ReactiveMongoRepository<Ride, ObjectId> {

}
