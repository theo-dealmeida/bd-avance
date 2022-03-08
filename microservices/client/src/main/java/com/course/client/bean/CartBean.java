package com.course.client.bean;

import java.util.List;

public class CartBean {
    private List<CartItemBean> products;

    public CartBean(List<CartItemBean> products) {
        this.products = products;
    }
}
