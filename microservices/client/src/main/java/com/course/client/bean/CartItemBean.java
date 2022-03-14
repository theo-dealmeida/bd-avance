package com.course.client.bean;

public class CartItemBean {
    private Long productId;
    private Integer quantity;

    public CartItemBean(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public CartItemBean() {
    }

    @Override
    public String toString() {
        return "CartItemBean{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
