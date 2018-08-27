package com.hubena.anytest;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 任何一个不需要保留的测试都可以在这里写
 * @author Zengxb
 *
 */
public class ForAnyTest {
	private static final Logger logger = LoggerFactory.getLogger(ForAnyTest.class);
	@Test
	public void testRuntime() throws InterruptedException {
		logger.debug("Runtime.getRuntime().totalMemory():{}", Runtime.getRuntime().totalMemory());
		logger.debug("Runtime.getRuntime().freeMemory():{}", Runtime.getRuntime().freeMemory());
		logger.debug("Runtime.getRuntime().availableProcessors():{}", Runtime.getRuntime().availableProcessors());
//		Thread hook = new Thread(Thread.currentThread().getThreadGroup(), () -> {
//			System.out.println("hook开始运行了");
//			}, "Hook_thread", 0) ; 
		Thread hook = new Thread(Thread.currentThread().getThreadGroup(), new Runnable() {
			
			@Override
			public void run() {
				System.out.println("hook开始运行了");
			}
		}, "Hook_thread", 0) ; 
		logger.debug("已注册完毕hook钩子");
		Runtime.getRuntime().addShutdownHook(hook);
//		TimeUnit.SECONDS.sleep(3);
		Runtime.getRuntime().exit(3);
	}
}
