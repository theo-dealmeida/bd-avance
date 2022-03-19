package com.course.order.entity;

import javax.persistence.*;

@Entity
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private Long cartId;
    private Double total;

    public Order(Long id, Long cartId, Double total) {
        this.id = id;
        this.cartId = cartId;
        this.total = total;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", cartId=" + cartId +
                '}';
    }
}
