package com.uade.tpo.cars_e_commerce.service;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.uade.tpo.cars_e_commerce.entity.ShopCart;
import com.uade.tpo.cars_e_commerce.repository.ShopCartLineRepository;
import com.uade.tpo.cars_e_commerce.repository.ShopCartRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopCartImpl implements ShopCartService {
    private final ShopCartRepository shopCartRepository;
    private final ShopCartLineRepository shopCartLineRepository;
    private final AtomicLong cartIdGenerator = new AtomicLong(0);

    @Override
    public ShopCart getCart(Long id) {
        ShopCart cart = shopCartRepository.findById(id)
                .orElseThrow(null);
        
        Double totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        return shopCartRepository.save(cart);
    }

    @Override
    public ShopCart clearCart(Long id) {
        ShopCart cart = getCart(id);
        shopCartLineRepository.deleteAllByCartId(id);
        cart.getItems().clear();
        shopCartRepository.deleteById(id);
        return cart;
    }

    @Override
    public Double getTotalPrice(Long id) {
        ShopCart cart= getCart(id);
        return cart.getTotalAmount();
    }

    public Long initializeNewCart(){
        ShopCart newCart = new ShopCart();
        Long newCartId = cartIdGenerator.incrementAndGet();
        newCart.setShopCartid(newCartId);
        return shopCartRepository.save(newCart).getShopCartid();
    }
    
}
