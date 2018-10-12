package com.ahold.alwaysdeliver.ride.repository;

import com.ahold.alwaysdeliver.ride.payload.Ride;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RideReactiveRepository extends ReactiveMongoRepository<Ride, ObjectId> {
    Flux<Ride> findAllByLocationIdAndDriverIdIsNull(ObjectId locationId);
    Flux<Ride> findAllByDriverIdIsNull();
    Flux<Ride> findAllByDriverId(ObjectId userId);
}