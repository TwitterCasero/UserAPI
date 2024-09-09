package com.twittercasero.users.infrastructure.repositories;

import com.twittercasero.users.domain.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByNickName(String nickName);
}
