package com.course.client.bean;

import java.util.List;

public class OrderBean {
    private int id;
    private long cartId;

    public OrderBean(long cartId, int id) {
        this.cartId = cartId;
        this.id = id;
    }

    public OrderBean(long cartId) {
        this.cartId = cartId;
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
