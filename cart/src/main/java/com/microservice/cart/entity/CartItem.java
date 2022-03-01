package com.microservice.cart.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CartItem {
    @Id
    @GeneratedValue
    private Long id;
    private Long productId;
    private Integer quantity;
}
