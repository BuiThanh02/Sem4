package com.example.demoseeder.repository;

import com.example.demoseeder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
