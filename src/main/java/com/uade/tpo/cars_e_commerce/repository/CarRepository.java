package com.uade.tpo.cars_e_commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uade.tpo.cars_e_commerce.entity.Cars;

public interface CarRepository extends JpaRepository<Cars, Long> {
    boolean existsByModelNameAndManufacturer(String modelName, String manufacturer);
}
