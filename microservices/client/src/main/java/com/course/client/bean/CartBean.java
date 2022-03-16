package com.course.client.bean;

import java.util.List;

public class CartBean {
    private int id;
    private List<CartItemBean> products;

    public CartBean(List<CartItemBean> products, int id) {
        this.products = products;
        this.id = id;
    }

    public List<CartItemBean> getProducts() {
        return products;
    }

    public void setProducts(List<CartItemBean> products) {
        this.products = products;
    }

    public int getId() {
        return this.id;
    }

    public CartBean() {

    }

    @Override
    public String toString() {
        return "CartBean{" +
                "products=" + products.toString() +
                '}';
    }
}
