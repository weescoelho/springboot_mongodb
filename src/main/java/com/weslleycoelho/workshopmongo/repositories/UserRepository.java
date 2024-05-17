package com.weslleycoelho.workshopmongo.repositories;

import org.springframework.stereotype.Repository;
import com.weslleycoelho.workshopmongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
