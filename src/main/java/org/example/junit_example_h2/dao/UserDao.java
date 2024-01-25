package org.example.junit_example_h2.dao;

import org.example.junit_example_h2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
}
