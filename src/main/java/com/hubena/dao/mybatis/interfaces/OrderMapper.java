package com.hubena.dao.mybatis.interfaces;

import org.springframework.stereotype.Repository;
import com.hubena.entity.Order;

@Repository
public interface OrderMapper {
	
	Order getOrder(Integer id);
}