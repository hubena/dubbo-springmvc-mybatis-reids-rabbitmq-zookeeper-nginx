package com.hubena.service.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class); 
	@Autowired
	OrderMapper orderMapper;

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.NOT_SUPPORTED, 
		rollbackFor = {Exception.class})
	public Order getOrder(Integer id) throws Exception {
		Order order1 = orderMapper.getOrder(id);
		logger.debug("-------Order order1 = orderMapper.getOrder(id):{}----------执行完毕", order1.toString());
		Order orderForUpate = new Order();
		orderForUpate.setId(id);
		orderForUpate.setOrderYear(2000);
		orderMapper.updateOrderYear(orderForUpate);
		Order order2 = orderMapper.getOrder(id);
		logger.debug("-------Order order2 = orderMapper.getOrder(id):{}----------执行完毕", order2.toString());
//		if (id == 3) {
//			throw new Exception("测试回滚");
//		}
		return order1;
	}

}
