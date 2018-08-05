package com.hubena.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.hubena.entity.Order;
import com.hubena.service.interfaces.OrderService;
import com.hubena.dao.mybatis.interfaces.OrderMapper;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderMapper orderMapper;

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Order getOrder(Integer id) {
		return orderMapper.getOrder(id);
	}

}
