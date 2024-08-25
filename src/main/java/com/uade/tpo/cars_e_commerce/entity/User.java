package com.uade.tpo.cars_e_commerce.entity;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String rol;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String home_address;
    @Column
    private int phone_number;

    //poner relacion de uno a mucho con ordenCompra
    @OneToMany (mappedBy = "user")
    private List<Order> ordenCompra;

    @OneToMany (mappedBy = "user")
    private List<ShopCart> carrito;
}