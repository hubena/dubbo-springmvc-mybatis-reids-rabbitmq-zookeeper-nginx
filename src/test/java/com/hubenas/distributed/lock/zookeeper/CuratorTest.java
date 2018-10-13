package com.hubenas.distributed.lock.zookeeper;

import java.util.concurrent.TimeUnit;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>测试Curator包实现锁.
 * @author Zengxb
 */
public class CuratorTest {
	private static final Logger logger = LoggerFactory.getLogger(CuratorTest.class);
	private static final String zookeeperConnectionString = "127.0.0.1:2183,127.0.0.1:2181,127.0.0.1:2182";
	private static CuratorFramework client;
	/**
	 * 可重入分布式锁.
	 */
	private static InterProcessMutex lock;
	
	@BeforeClass
	public static void BeforeClass() {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		client = CuratorFrameworkFactory.newClient(zookeeperConnectionString, 5000000,5000000,retryPolicy);
		client.start();
		lock = new InterProcessMutex(client, "/lock1");
	}
	@Test
	public void testCuratorLock() throws Exception {
		
		// 除非获得锁否则，阻塞直到超时，超过时间未获得锁则返回false
		if (lock.acquire(3000, TimeUnit.MILLISECONDS)) {
		    try {
		        // do some work inside of the critical section here
		    	
		    	logger.debug("获得了可重入锁");
		    	TimeUnit.SECONDS.sleep(5);
		    	logger.debug("准备释放可重入锁");
		    } finally {
		    	// 释放锁
		        lock.release();
		    }
		}
	}
}
