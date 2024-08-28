package com.uade.tpo.cars_e_commerce.entity;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class ShopCart {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long shopCartid;

        @Column
        private Double total;

        @OneToOne
        @JoinColumn(name = "user_id", nullable = false)
        private User user;

        @OneToMany(mappedBy = "shopCart")
        private List<ShopCartLine> shopCartLine;
}