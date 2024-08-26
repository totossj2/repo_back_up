package com.uade.tpo.cars_e_commerce.controllers;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.cars_e_commerce.entity.Cars;
import com.uade.tpo.cars_e_commerce.entity.Category;
import com.uade.tpo.cars_e_commerce.entity.dto.CarRequest;
import com.uade.tpo.cars_e_commerce.exceptions.CarDuplicateException;
import com.uade.tpo.cars_e_commerce.service.CarService;

@RestController
@RequestMapping("cars")
public class CarsController {

    @Autowired
    private CarService carService;

    @PostMapping
    public ResponseEntity<Cars> createCar(@RequestBody CarRequest carRequest) throws CarDuplicateException {
        Cars car = new Cars();
        car.setManufacturer(carRequest.getManufacturer());
        car.setModelName(carRequest.getModelName());
        car.setModelYear(carRequest.getModelYear());
        car.setColor(carRequest.getColor());
        car.setPrice(carRequest.getPrice());
        car.setStock(carRequest.getStock());

        Category category = carRequest.getCategory(); 
        car.setCategory(category);

        Cars createdCar = carService.createCar(car);

        return ResponseEntity.ok(createdCar);
    }
    
    @GetMapping
    public ResponseEntity<List<CarRequest>> getAllCars() {
        List<Cars> cars = carService.getAllCars();
        List<CarRequest> carResponseDTOs = cars.stream().map(car -> {
            CarRequest dto = new CarRequest();
            dto.setId(car.getCarId());
            dto.setModelName(car.getModelName());
            dto.setManufacturer(car.getManufacturer());
            dto.setModelYear(car.getModelYear());
            dto.setColor(car.getColor());
            dto.setPrice(car.getPrice());
            dto.setStock(car.getStock());
            dto.setCategory(car.getCategory());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(carResponseDTOs);
    }
}
