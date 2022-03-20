package com.course.client.bean;

import java.util.List;

public class OrderBean {
    private int id;
    private long cartId;
    private Double total;

    public OrderBean(long cartId, int id, Double total) {
        this.cartId = cartId;
        this.id = id;
        this.total = total;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public OrderBean(long cartId) {
        this.cartId = cartId;
    }

    public OrderBean(long cartId, Double total) {
        this.cartId = cartId;
        this.total = total;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }


    public int getId() {
        return this.id;
    }

    public OrderBean() {

    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "cartId=" + cartId +
                '}';
    }
}
