package com.codingdojo.localEvents1.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.codingdojo.localEvents1.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByEmail(String email);
}
