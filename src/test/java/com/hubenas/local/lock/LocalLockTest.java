package com.hubenas.local.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>测试本地锁synchronized与ReentrantLock.
 * @author 曾谢波
 * @since 2018年9月29日
 */
public class LocalLockTest {
	private static final Logger logger = LoggerFactory.getLogger(LocalLockTest.class);
	
	private SynLock synLock = new SynLock();
	
	@Test
	public void testSynchronizedLock() throws InterruptedException {
		synLock.setAge(33);
		synLock.setName("小明");
		CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
		new Thread(()->{
			try {
				cyclicBarrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
			synchronized (synLock) {
				
				logger.debug("进入线程Thread锁synchronized");
			}
		}).start();
		
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			logger.debug("开始执行线程测试是否可以操作Lock了");
			logger.debug("打印synLock:{}", synLock.toString());
			
		}).start();
		
		synchronized (synLock) {
			try {
				cyclicBarrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
			logger.debug("进入了主线程锁");
			TimeUnit.SECONDS.sleep(5);
			logger.debug("要放弃主线程锁了"); 
		}
		
	}
}
