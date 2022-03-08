package com.course.client.bean;

public class CartItemBean {
    private Long productId;
    private Integer quantity;

    public CartItemBean(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
