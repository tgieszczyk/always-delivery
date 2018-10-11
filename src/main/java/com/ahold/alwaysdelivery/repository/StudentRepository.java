package com.ahold.alwaysdelivery.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
//@Component
public interface StudentRepository extends ReactiveMongoRepository<Student, ObjectId> {

}
