package com.example.amazon.test.task.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, String> {
    UserEntity findByUserEmail(String userEmail);
}
