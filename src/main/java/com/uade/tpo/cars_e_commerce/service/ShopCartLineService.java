package com.uade.tpo.cars_e_commerce.service;

public interface ShopCartLineService {
    void addItemToCart(Long cartId, Long productId, Long quantity);
    void removeItemFromCart(Long cartId, Long productId);
    void updateItemQuantity(Long cartId, Long productId, Long quantity);
    
}
