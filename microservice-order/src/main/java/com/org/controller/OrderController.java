package com.org.controller;

import com.org.service.IOrderService;
import com.org.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by KaBu on 2018/11/8.
 */
@RestController
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping(value = "/order/{id}")
    public OrderVo getOrder(@PathVariable("id") Long id){
        return orderService.findByOrderId(id);
    }

}
