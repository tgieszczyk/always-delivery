package com.ahold.alwaysdelivery.user.repository;

import com.ahold.alwaysdelivery.user.payload.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, ObjectId> {

}
