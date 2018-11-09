package com.org.vo;

import com.org.entity.PrdOrder;
import com.org.entity.Product;

import java.util.Date;
import java.util.List;

/**
 * Created by KaBu on 2018/11/8.
 */
public class OrderVo {

    private Long id;

    private Long userId;

    private Date createTime;

    public OrderVo() {
    }

    public OrderVo(PrdOrder prdOrder, List<Product> productList) {
        this.id = prdOrder.getId();
        this.userId = prdOrder.getUserId();
        this.createTime = prdOrder.getCreateTime();
        this.productList = productList;
    }

    private List<Product> productList;

    public Long getId() {
        return id;
    }

    public OrderVo setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public OrderVo setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public OrderVo setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public OrderVo setProductList(List<Product> productList) {
        this.productList = productList;
        return this;
    }
}
