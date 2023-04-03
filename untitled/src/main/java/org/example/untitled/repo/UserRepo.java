package org.example.untitled.repo;

import org.example.untitled.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {
    User findByUsername(String Username);
}