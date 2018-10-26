package com.hubena.service.interfaces;

import com.hubena.entity.Order;

public interface OrderService {
	Order getOrder(Integer id) throws Exception;
}
