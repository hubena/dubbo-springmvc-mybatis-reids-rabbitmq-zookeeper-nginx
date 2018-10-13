package com.hubenas.FactoryBean;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hubenas.FactoryBean.Configuration.FactoryBeanSpringConfiguration;
import com.hubenas.entity.IPerson;

/**
 * 测试FactoryBean.
 * @author Zengxb
 *
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {FactoryBeanSpringConfiguration.class})
public class FactoryBeanTest {
	private static final Logger logger = LoggerFactory.getLogger(FactoryBeanTest.class);
	
	@Resource(name = "personFactoryBean")
	IPerson iPerson;
	
	@Resource(name = "personFactoryBean")
	IPerson iPerson2;
	
	
	@Test
	public void testFactoryBean() {
		iPerson.eatSome();
		
		// 结果为：false，说明scope为prototype的类多次注入的类不同
		logger.error(String.valueOf((iPerson == iPerson2)));
	}
}
