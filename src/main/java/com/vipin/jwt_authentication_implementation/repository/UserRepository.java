package com.vipin.jwt_authentication_implementation.repository;
import com.vipin.jwt_authentication_implementation.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByUsername(String username);
}
