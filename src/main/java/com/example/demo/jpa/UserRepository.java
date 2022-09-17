package com.example.demo.jpa;

import com.example.demo.repository.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
