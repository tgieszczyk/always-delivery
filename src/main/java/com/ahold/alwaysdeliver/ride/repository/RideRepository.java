package com.ahold.alwaysdeliver.ride.repository;

import com.ahold.alwaysdeliver.ride.payload.Ride;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends MongoRepository<Ride, ObjectId> {
}