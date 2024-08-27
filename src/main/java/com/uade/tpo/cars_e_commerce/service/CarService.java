package com.uade.tpo.cars_e_commerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.cars_e_commerce.entity.Cars;
import com.uade.tpo.cars_e_commerce.exceptions.CarDuplicateException;
import com.uade.tpo.cars_e_commerce.exceptions.CarNotFoundException;

public interface CarService {

    Page<Cars> getCars(PageRequest pageRequest);

    List<Cars> getAllCars();

    Optional<Cars> getCarById(Long carId);

    Cars createCar(Cars car) throws CarDuplicateException;

    Optional<Cars> getCarByManufacturer(String manufacturer) throws CarNotFoundException;

    Optional<Cars> getCarByPrice(Double price) throws CarNotFoundException;

    Optional<Cars> getCarByRangePrice(Double price_min, Double price_max) throws CarNotFoundException;

    Optional<Cars> getCarByColor(String Color) throws CarNotFoundException;

    Optional<Cars> getCarByModelName(String model_name) throws CarNotFoundException;

    Optional<Cars> getCarByModelYear(Integer model_year) throws CarNotFoundException;
}
