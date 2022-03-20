package com.course.order.entity;

import javax.persistence.*;

@Entity
public class Commande {
    @Id
    @GeneratedValue
    private Long id;
    private Long cartId;
    private Double total;

    public Commande(Long id, Long cartId, Double total) {
        this.id = id;
        this.cartId = cartId;
        this.total = total;
    }

    public Commande(Long cartId) {
        this.cartId = cartId;
    }

    public Commande(Long cartId, Double total) {
        this.cartId = cartId;
        this.total = total;
    }

    public Commande() {
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
