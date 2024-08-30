package com.uade.tpo.cars_e_commerce.entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShopCartLine {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ShopCartLineid;

    @Column
    private Long quantity;

    @Column
    private Double unitPrice;

    @Column 
    private Double totalPrice;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "cart_id", nullable = false)
    private ShopCart shopCart;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Cars car;
    
    public void setTotalPrice() {
        this.totalPrice = this.quantity * this.unitPrice;
    }


    
}
