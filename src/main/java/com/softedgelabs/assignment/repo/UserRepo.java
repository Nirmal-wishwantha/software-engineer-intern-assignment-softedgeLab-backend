package com.softedgelabs.assignment.repo;

import com.softedgelabs.assignment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
