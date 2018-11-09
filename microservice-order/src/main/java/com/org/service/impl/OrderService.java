package com.org.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.org.dao.IOrderDao;
import com.org.dao.IOrderDetailDao;
import com.org.entity.PrdOrder;
import com.org.entity.OrderDetail;
import com.org.entity.Product;
import com.org.service.IOrderService;
import com.org.util.GeneralUtil;
import com.org.util.KeyGetter;
import com.org.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KaBu on 2018/11/8.
 */
@Service
public class OrderService implements IOrderService {

    @Autowired
    private IOrderDao orderDao;
    @Autowired
    private IOrderDetailDao orderDetailDao;
    @Autowired
    private RestTemplate restTemplate;

    private String prdUrl = "http://app-product/detail/{id}";

    @Override
    public OrderVo findByOrderId(Long id){
        PrdOrder prdOrder = orderDao.getOne(id);
        if(prdOrder == null){
            return null;
        }
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(id);
        //获取商品ID集合
        List<Long> productIds = GeneralUtil.extractKeys(orderDetailList, new KeyGetter<Long, OrderDetail>() {
            @Override
            public Long getKey(OrderDetail orderDetail) {
                return orderDetail.getProductId();
            }
        });
        OrderVo orderVo = new OrderVo(prdOrder,findProductListByIds(productIds));
        return orderVo;
    }

    private List<Product> findProductListByIds(List<Long> productIds){
        List<Product> productList = new ArrayList<>();

        for(Long id : productIds){
            productList.add(
                    restTemplate.getForObject(prdUrl,Product.class,id));
            System.out.println("订单系统调用商品服务");
        }
        return productList;
    }
}
