package com.org.dao;

import com.org.entity.PrdOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by KaBu on 2018/11/8.
 */
public interface IOrderDao extends JpaRepository<PrdOrder,Long> {

}
