package com.org.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by KaBu on 2018/11/8.
 */
@Entity
public class PrdOrder {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private Date createTime;



    public Long getId() {
        return id;
    }

    public PrdOrder setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public PrdOrder setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public PrdOrder setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public String toString() {
        return "PrdOrder{" +
                "id=" + id +
                ", userId=" + userId +
                ", createTime=" + createTime +
                '}';
    }
}
