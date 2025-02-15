package com.uade.tpo.cars_e_commerce.entity;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class User {

    public User() {}

    public User(Long id, String username, String password, String email, String name, String surname, String home_address, String phone_number, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.home_address = home_address;
        this.phone_number = phone_number;
        this.role = role;

    }

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
    private String role;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String home_address;

    @Column
    private String phone_number;

    //poner relacion de uno a mucho con ordenCompra
    @OneToMany (mappedBy = "user")
    private List<Order> ordenCompra;

    @OneToOne (mappedBy = "user")
    private ShopCart carrito;
    
}