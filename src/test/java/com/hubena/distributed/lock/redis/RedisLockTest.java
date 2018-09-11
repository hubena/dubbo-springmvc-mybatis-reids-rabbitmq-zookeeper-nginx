package com.hubena.distributed.lock.redis;

import java.util.UUID;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

/**
 * <p>测试Redis分布式锁.</p>
 * @author Zengxb
 *
 */
public class RedisLockTest {
	private static final Logger logger = LoggerFactory.getLogger(RedisLockTest.class);
	
	@Test
	public void testRedisLock() {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		String uuid = UUID.randomUUID().toString();
		boolean isLock = RedisLock.tryDistributedLock(jedis, "lockKey", uuid, 5000);
		if (isLock) {
			logger.error("已上锁。锁记录为：{}", jedis.get("lockKey"));
		}
		
		isLock = RedisLock.tryDistributedLock(jedis, "lockKey", uuid, 5000);
		// 当未取到锁时循环取锁，直到成功
		long currentTime = System.currentTimeMillis();
		while (!isLock) {
			isLock = RedisLock.tryDistributedLock(jedis, "lockKey", uuid, 5000);
		}
		long getLockTime = System.currentTimeMillis() - currentTime;
		logger.error("经过{}时间获得锁", getLockTime); 
		
		
		
		
		
	}
}
