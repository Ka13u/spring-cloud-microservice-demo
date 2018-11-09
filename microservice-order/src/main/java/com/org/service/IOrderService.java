package com.org.service;

import com.org.vo.OrderVo;

/**
 * Created by KaBu on 2018/11/8.
 */
public interface IOrderService{

    OrderVo findByOrderId(Long id);
}
