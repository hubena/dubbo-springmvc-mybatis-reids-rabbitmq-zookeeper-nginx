package com.hubena.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.hubena.entity.Order;
import com.hubena.service.interfaces.OrderService;

@Controller
@RequestMapping(value="/role")
public class RoleController{
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	@Autowired
	OrderService roleService;
//	@Value("#{rabbitMQProperty['addresses']}")
//	@Value("#{rabbitMQProperty.addresses?:'AAAAAAA'}")
//	private String address;
//	@Autowired
//	RabbitMQProperty rabbitMQProperty;
//	@Autowired 
//	RedisTemplate redisTemplate;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getRole", method = RequestMethod.GET)
	public ModelAndView getRole(@RequestParam("id") Integer id) {
		Order order = roleService.getOrder(id);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("roleDetails");
		// 给数据模型添加一个角色对象
		mv.addObject("order", order);
		///先注释掉，后期完善redis再取消
/*		redisTemplate.opsForValue().set("order_1", order);
		Order role2 = (Order) redisTemplate.opsForValue().get("order_1");
		System.out.println(order.getCustomer());
		
		@SuppressWarnings("unchecked")
		String retVal = (String) redisTemplate.execute((@SuppressWarnings("rawtypes") RedisOperations ops) ->{
			ops.boundValueOps("mykey").set("myvalue");
			String value = (String) ops.boundValueOps("mykey").get();
			return value;
		});*/
		
//		System.out.println(rabbitMQProperty.getFanoutExchange());
//		System.out.println(rabbitMQProperty.getAddresses());
//		System.out.println(address);
//		logger.error("address:{}", address);
		return mv;
	}

}
