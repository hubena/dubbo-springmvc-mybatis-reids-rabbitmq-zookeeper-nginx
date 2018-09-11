package com.hubena.distributed.lock.redis;

import java.util.Collections;

import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

public class RedisLock {
	private static final String LOCK_RESULT = "OK";
	private static final String SET_IF_NOT_EXIST = "NX";
	private static final String EXPIRE_TIME_MILLISECONDS = "PX";
	
	private static final Long RELEASE_SUCCESS = 1L;
	
	/**
	 *  redis加分布式锁方法.<p>
	 *  {@link Jedis#set(String, String, String, String, long)}方法对应参数如下：
	 *  SET key value [expiration EX seconds|PX milliseconds] [NX|XX]
	 *  EX seconds – 设置键key的过期时间，单位时秒. <br>
	 *  PX milliseconds – 设置键key的过期时间，单位时毫秒. <br>
	 *  NX – 只有键key不存在的时候才会设置key的值. <br>
	 *  XX – 只有键key存在的时候才会设置key的值. <br>
	 * @param jedis
	 * @param lockKey 锁名，作为键存储
	 * @param lockId 上锁客户端标识，用于解锁时判断是否与加锁者为同一客户端
	 * @param expireTime 超时时间，为毫秒
	 */
	public static boolean  tryDistributedLock(Jedis jedis, String lockKey, String lockId, long expireTime) {
		String result = jedis.set(lockKey, lockId, SET_IF_NOT_EXIST, EXPIRE_TIME_MILLISECONDS, expireTime);
		if (LOCK_RESULT.equals(result)) {
			return true;
		}
		return false;
	}
	
	/**
	 * <p>解锁分布式锁方法.</p> 
	 * @param jedis
	 * @param lockKey
	 * @param lockId
	 * @return
	 */
	public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String lockId) {
		String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
		Object result = jedis.eval(luaScript, Collections.singletonList(lockKey), Collections.singletonList(lockId));
		if (RELEASE_SUCCESS.equals(result)) {
			return true;
		}
		return false;
	}
}
