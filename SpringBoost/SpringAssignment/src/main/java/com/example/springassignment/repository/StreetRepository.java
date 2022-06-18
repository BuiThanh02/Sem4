package com.example.springassignment.repository;

import com.example.springassignment.entity.District;
import com.example.springassignment.entity.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StreetRepository extends JpaRepository<Street, UUID> {
    @Query("SELECT s FROM District s where s.name = :name")
    List<Street> findStreetByName(@Param("name") String name);

    Street findStreetsByDistrict(District district);
}
