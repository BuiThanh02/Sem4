package com.example.springassignment.repository;

import com.example.springassignment.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DistrictRepository extends JpaRepository<District, UUID> {
}
