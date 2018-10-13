package com.hubenas.distributed.lock.Redisson;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hubenas.FactoryBean.Configuration.FactoryBeanSpringConfiguration;

/**
 * <p>测试Redisson分布式锁.
 * @author 曾谢波
 * @since 2018年9月26日
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RedissonConfiguration.class})
public class RedissonTest {
	private static final Logger logger = LoggerFactory.getLogger(RedissonTest.class);
	
	@Autowired
	Redisson redisson;
	
	@Test
	public void testRedissonLock() throws InterruptedException {
		
		RLock rLock = redisson.getLock("keyLockRedisson");
		// 设置另一个线程也争夺锁
		new Thread(()->{ 
			logger.error("进入子线程");
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			rLock.lock();
			logger.error("子线程获取到锁了");
			rLock.unlock();
			
		}).start();
		logger.error("进入主线程");
		rLock.lock(60, TimeUnit.SECONDS); //设置60秒自动释放锁  （默认是30秒自动过期）
		
		TimeUnit.SECONDS.sleep(20);
		
		rLock.unlock();
		logger.error("主线程释放锁了");
	}
}
