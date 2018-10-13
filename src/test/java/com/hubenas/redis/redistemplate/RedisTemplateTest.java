package com.hubenas.redis.redistemplate;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>测试RedisTemplate
 * @author 曾谢波
 * @since 2018年9月17日
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RedisConfiguration.class})
public class RedisTemplateTest {
	private static final Logger logger = LoggerFactory.getLogger(RedisTemplateTest.class);
	@Autowired
	StringRedisTemplate redisTemplate;
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void testRedisTemplate() throws ClassNotFoundException {
		@SuppressWarnings("rawtypes")
		SessionCallback<Object> sessionCallback = new SessionCallback() {

			@Override
			public Object execute(RedisOperations operations) throws DataAccessException {
				long count = 0;
				long time = 0;
				logger.debug("开始时间：{}", time = System.currentTimeMillis());
				while (count < 1000000L) {
					operations.opsForValue().set("keyTest", "valueTest" + count);
					count++;
//					try {
//						TimeUnit.SECONDS.sleep(15);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					logger.error("count:{}", operations.opsForValue().get("keyTest"));
				}
				Runtime.getRuntime().gc();
				
				logger.debug("结束时间：{}", System.currentTimeMillis() - time);
				
				return null;
			}
		};
		redisTemplate.execute(sessionCallback);
//		List<RedisClientInfo> list = redisTemplate.getConnectionFactory().getConnection().getClientList();
//		for (RedisClientInfo redisClientInfo : list) {
//			logger.error("getDatabaseId:{}", redisClientInfo.getDatabaseId());
//		}
//		redisTemplate.opsForValue().set("keyTest", String.valueOf(88888888));
//		logger.error("redis_db:{}", ((Jedis)redisTemplate.getConnectionFactory().getConnection().getNativeConnection()).getDB());
	}
}
