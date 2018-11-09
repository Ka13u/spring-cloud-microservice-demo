package com.org.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by KaBu on 2018/11/8.
 */
@Entity
public class OrderDetail {

    @Id
    @GeneratedValue
    private Long id;

    private Long orderId;

    private Long productId;

    public Long getId() {
        return id;
    }

    public OrderDetail setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getOrderId() {
        return orderId;
    }

    public OrderDetail setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public Long getProductId() {
        return productId;
    }

    public OrderDetail setProductId(Long productId) {
        this.productId = productId;
        return this;
    }
}
