package com.springboot.Mall.repository;

import com.springboot.Mall.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
