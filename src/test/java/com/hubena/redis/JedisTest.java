package com.hubena.redis;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

/**
 * 测试jedis包.
 * @author Zengxb
 *
 */
public class JedisTest {
	private static final Logger logger = LoggerFactory.getLogger(JedisTest.class);
	
	@Test
	public void testJedis() {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
//		jedis.auth("password"); // 如果需要密码
		
		int count = 0; // 执行次数
		long start = System.currentTimeMillis(); //开始毫秒数
		try {
			while (true) {
				long end = System.currentTimeMillis();
				if (end - start >= 1000) {
					break; // 执行时间大于一秒退出
				}
				/* 以下命令常用来实现分布式锁加锁操作：
				 * SET key value [EX seconds] [PX milliseconds] [NX|XX]
				 * EX seconds – 设置键key的过期时间，单位时秒
				 * PX milliseconds – 设置键key的过期时间，单位时毫秒
				 * NX – 只有键key不存在的时候才会设置key的值
				 * XX – 只有键key存在的时候才会设置key的值
				 */
//				jedis.set("key22", "value22", "NX", "PX", 1000);
				jedis.set("key22", "value22");
				count++;
			}
		} finally {
			jedis.close();
		}
		
		logger.error("count值为:{}", count);

	}
}
