package com.hubenas.reflect;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>测试Class类方法.
 * @author 曾谢波
 * @since 2018年9月20日
 */
public class ClassTest {
	private static final Logger logger = LoggerFactory.getLogger(ClassTest.class);
	
//	@Test
	public void testForName() throws ClassNotFoundException {
		logger.error("Class.forName(\"com.hubena.reflect.ClassTest\"):{}", Class.forName("com.hubena.reflect.CarDo"));
	}
	 
	@Test
	public void testLoadClass() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		logger.error("System.getProperty(\"sun.boot.class.path\"):{}", System.getProperty("sun.boot.class.path"));
//		logger.error("ClassLoader.getSystemClassLoader().loadClass(\"\\\"com.hubena.reflect.CarDo\\\"\"):{}", ClassLoader.getSystemClassLoader().loadClass("com.hubena.reflect.CarDo"));
		Class class1 = ClassLoader.getSystemClassLoader().loadClass("com.hubena.reflect.CarDo");
		class1.newInstance();
	}
}
