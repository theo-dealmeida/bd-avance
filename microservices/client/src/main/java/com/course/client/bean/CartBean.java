package com.course.client.bean;

import java.util.List;

public class CartBean {
    private List<CartItemBean> products;

    public CartBean(List<CartItemBean> products) {
        this.products = products;
    }

    public List<CartItemBean> getProducts() {
        return products;
    }

    public void setProducts(List<CartItemBean> products) {
        this.products = products;
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
