package com.uade.tpo.cars_e_commerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.cars_e_commerce.entity.Cars;
import com.uade.tpo.cars_e_commerce.exceptions.CarDuplicateException;

public interface CarService {

    Page<Cars> getCars(PageRequest pageRequest);

    List<Cars> getAllCars();

    Optional<Cars> getCarById(Long carId);

    Cars createCar(Cars car) throws CarDuplicateException;
}
