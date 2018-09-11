package com.hubena.anytest;

import java.text.MessageFormat;

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
	
//	@Test
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
	
//	@Test
	public void testMessageFormat() {
		 int fileCount = 1273;
		 String diskName = "MyDisk";
		 Object[] testArgs = {new Long(fileCount), diskName};

		 MessageFormat form = new MessageFormat(
		     "The disk \"{1}\" contains {0} file(s).");

		 System.out.println(form.format(testArgs));
	}
	
	@Test
	public void testSlf4jPatternFormat() {
		logger.error("测试异常日志打印:", new Exception("自定义异常Exception"));
		
		logger.debug("测试占位符{},--{}--{}--{}", "第一个", "第二个", "第三个");
	}
}
