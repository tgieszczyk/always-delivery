package com.ahold.alwaysdeliver.user.repository;

import com.ahold.alwaysdeliver.user.payload.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
}
