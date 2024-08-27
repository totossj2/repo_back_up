package com.uade.tpo.cars_e_commerce.controllers;
import java.net.URI;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.uade.tpo.cars_e_commerce.entity.Cars;
import com.uade.tpo.cars_e_commerce.entity.Category;
import com.uade.tpo.cars_e_commerce.entity.dto.CarRequest;
import com.uade.tpo.cars_e_commerce.exceptions.CarDuplicateException;
import com.uade.tpo.cars_e_commerce.exceptions.CarNotFoundException;
import com.uade.tpo.cars_e_commerce.service.CarService;



@RestController
@RequestMapping("cars")
public class CarsController {

    @Autowired
    private CarService carService;

@PostMapping
    public ResponseEntity<Object> createCar(@RequestBody CarRequest carRequest) throws CarDuplicateException {
        Cars car = new Cars();
        car.setManufacturer(carRequest.getManufacturer());
        car.setModelName(carRequest.getModelName());
        car.setModelYear(carRequest.getModelYear());
        car.setColor(carRequest.getColor());
        car.setPrice(carRequest.getPrice());
        car.setStock(carRequest.getStock());
        Category category = carRequest.getCategory(); 
        car.setCategory(category);
        Cars result = carService.createCar(car);
        URI location = URI.create("/cars/" + result.getCarId());
        return ResponseEntity.created(location).body(result);
}
    
@GetMapping
    public ResponseEntity<Page<Cars>> getCars(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(carService.getCars(PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(carService.getCars(PageRequest.of(page, size)));
    }

    @GetMapping("/{manufacturer}")
    public ResponseEntity<Cars> getCarByManufacturer(@PathVariable String manufacturer) throws CarNotFoundException{
        Optional<Cars> result = carService.getCarByManufacturer(manufacturer);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{price}")
    public ResponseEntity<Cars> getCarByPrice(@PathVariable Double price) throws CarNotFoundException{
        Optional<Cars> result = carService.getCarByPrice(price);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{range_price}")
    public ResponseEntity<Cars> getCarByRangePrice(@PathVariable Double price_min, @PathVariable Double price_max) throws CarNotFoundException{
        Optional<Cars> result = carService.getCarByRangePrice(price_min, price_max);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{color}")
    public ResponseEntity<Cars> getCarByColor(@RequestParam String Color) throws CarNotFoundException{
        Optional<Cars> result = carService.getCarByColor(Color);
        if(result.isPresent())
            return ResponseEntity.ok(result.get());
        return ResponseEntity.noContent().build();
    } 

    @GetMapping("/{model_name}")
    public ResponseEntity<Cars> getCarByModelName(@RequestParam String model_name) throws CarNotFoundException{
        Optional<Cars> result = carService.getCarByModelName(model_name);
        if(result.isPresent())
            return ResponseEntity.ok(result.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{model_name}")
    public ResponseEntity<Cars> getCarByModelYear(@RequestParam Integer model_year) throws CarNotFoundException{
        Optional<Cars> result = carService.getCarByModelYear(model_year);
        if(result.isPresent())
            return ResponseEntity.ok(result.get());
        return ResponseEntity.noContent().build();
    }


    
    
}
