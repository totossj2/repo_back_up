package com.uade.tpo.cars_e_commerce.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orden_compra")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;
    
    @Column
    private String status;

    @Column
    private Timestamp createdAt;
    @Column
    private Long total;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
}