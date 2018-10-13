package com.hubenas.string;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

public class StringTest {
	private static final Logger logger = LoggerFactory.getLogger(StringTest.class);
	@Test
	public void testString() {
		
		String aString = "ja";
		String bString = "va";
		String cString = "java";
		String dString = aString + bString; // 反编译会发现是通过StringBuilder新建对象实现相加的
		logger.debug("dString == cString:{}", dString == cString); // false
		
		
		String eString = "java";
		String fString = "ja" + "va"; // 这是直接合并为"java",然后常量池中寻找相等对象
		logger.debug("eString == fString:{}", eString == fString); // true
	
		String gString = new String("java"); // new方法永远都新建对象
		logger.debug("eString == gString:{}", eString == gString); // false
		
	}
}
