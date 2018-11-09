package com.org.dao;

import com.org.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by KaBu on 2018/11/8.
 */
public interface IOrderDetailDao extends JpaRepository<OrderDetail,Long> {

    List<OrderDetail> findByOrderId(Long orderId);
}
