package com.uade.tpo.cars_e_commerce.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.uade.tpo.cars_e_commerce.entity.Cars;
import com.uade.tpo.cars_e_commerce.entity.ShopCart;
import com.uade.tpo.cars_e_commerce.entity.ShopCartLine;
import com.uade.tpo.cars_e_commerce.exceptions.ResourceNotFoundException;
import com.uade.tpo.cars_e_commerce.repository.ShopCartLineRepository;
import com.uade.tpo.cars_e_commerce.repository.ShopCartRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopCartLineImpl implements ShopCartLineService {
    private final ShopCartLineRepository shopCartLineRepository;
    private final ShopCartRepository ShopCartRepository; 
    private final CarService carService;
    private final ShopCartService shopCartService;

    @Override
    public void addItemToCart(Long cartId, Long productId, Long quantity) {
        ShopCart cart = shopCartService.getCart(cartId);
        Optional<Cars> carOption = carService.getCarById(productId);
        Cars car;

        if (carOption.isPresent()) {
            car = carOption.get();
        } else {
            car = null;
        }

        ShopCartLine cartItem = cart.getItems()
                                .stream()
                                .filter(item -> item.getCar().getCarId().equals(productId))
                                .findFirst().orElse(new ShopCartLine());
        if (cartItem.getCar() == null) {
            cartItem.setShopCart(cart);
            cartItem.setCar(car);
            cartItem.setQuantity(quantity);
            cartItem.setUnitPrice(car.getPrice());
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        cartItem.setTotalPrice();
        cart.addItem(cartItem);
        shopCartLineRepository.save(cartItem);
        ShopCartRepository.save(cart);

    }

    @Override
    public void removeItemFromCart(Long cartId, Long productId) {
        ShopCart cart = shopCartService.getCart(cartId);
        ShopCartLine itemToRemove = getCartItem(cartId, productId);
        cart.removeItem(itemToRemove);
        ShopCartRepository.save(cart);
    }

    @Override
    public void updateItemQuantity(Long cartId, Long productId, Long quantity) {
        ShopCart cart = shopCartService.getCart(cartId);
        cart.getItems()
            .stream()
            .filter(item -> item.getCar().getCarId().equals(productId))
            .findFirst()
            .ifPresent(item -> {
                item.setQuantity(quantity);
                item.setUnitPrice(item.getCar().getPrice());
                item.setTotalPrice();
            });

            Double totalAmount = cart.getTotalAmount();
            cart.setTotalAmount(totalAmount);
            ShopCartRepository.save(cart);
    }


    public ShopCartLine getCartItem(Long cartId, Long productId) {
        ShopCart cart = shopCartService.getCart(cartId);
        return cart.getItems()
            .stream()
            .filter(item -> item.getCar().getCarId().equals(productId))
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException("car not found"));
    }

}
