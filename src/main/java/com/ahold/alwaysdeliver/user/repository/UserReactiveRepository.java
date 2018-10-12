package com.ahold.alwaysdeliver.user.repository;

import com.ahold.alwaysdeliver.user.payload.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReactiveRepository extends ReactiveMongoRepository<User, ObjectId> {
}
