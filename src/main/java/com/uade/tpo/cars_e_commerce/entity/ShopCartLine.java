package com.uade.tpo.cars_e_commerce.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ShopCartLine {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ShopCartLineid;

    private Long cartIdentifier;

    @Column
    private Integer quantity;

    @Column
    private Double linePrice;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private ShopCart shopCart;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Cars car;
    
}
