package com.hubena.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hubena.entity.Order;
import com.hubena.service.interfaces.OrderService;

@Controller
@RequestMapping(value = "/role")
public class RoleController {
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
		Order order = null;
		try {
			order = roleService.getOrder(id);
		} catch (Exception e1) {
			logger.error("异常测试", e1);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("roleDetails");
		// 给数据模型添加一个角色对象
		mv.addObject("order", order);
		/// 先注释掉，后期完善redis再取消
		/*
		 * redisTemplate.opsForValue().set("order_1", order); Order role2 = (Order)
		 * redisTemplate.opsForValue().get("order_1");
		 * System.out.println(order.getCustomer());
		 * 
		 * @SuppressWarnings("unchecked") String retVal = (String)
		 * redisTemplate.execute((@SuppressWarnings("rawtypes") RedisOperations ops) ->{
		 * ops.boundValueOps("mykey").set("myvalue"); String value = (String)
		 * ops.boundValueOps("mykey").get(); return value; });
		 */

//		System.out.println(rabbitMQProperty.getFanoutExchange());
//		System.out.println(rabbitMQProperty.getAddresses());
//		System.out.println(address);
//		logger.error("address:{}", address);
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1?useSSL=false", "root", "admin");
			statement = connection
					.prepareStatement("select id, orderYear, orderPrice, customer from test1.order where id=?");
			statement.setInt(1, 1);
			resultSet = statement.executeQuery();
			Order order2 = new Order();
			while (resultSet.next()) {
				Integer id2 = resultSet.getInt("id");
				order2.setId(id2);
				String customer = resultSet.getString("customer");
				order2.setCustomer(customer);
				logger.debug("order2:{}", order2.toString());
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			
			if (connection != null) {// 3.关闭连接 （记住一定要先关闭前面的1.2.然后在关闭连接）
				try {
					connection.close();
				} catch (Exception e) {
					logger.error("数据库资源释放错误.", e);
				}
			}
			
			if (statement != null) {// 2.关闭声明的对象
				try {
					statement.close();
				} catch (SQLException e) {
					logger.error("数据库资源释放错误.", e);
				}
			}
			if (resultSet != null) {// 1.关闭结果集
				try {
					resultSet.close();
				} catch (SQLException e) {
					logger.error("数据库资源释放错误.", e);
				}
			}
		}

		return mv;
	}

}
