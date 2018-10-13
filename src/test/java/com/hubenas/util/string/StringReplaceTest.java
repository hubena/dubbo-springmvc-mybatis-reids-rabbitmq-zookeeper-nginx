package com.hubenas.util.string;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.FinalizablePhantomReference;

/**
 * @author Zengxb
 *
 */
public class StringReplaceTest {
	private static final Logger logger = LoggerFactory.getLogger(StringReplaceTest.class);
	
	private static final String OLD_KEY = "#";
	private static final String NEW_KEY = " ";
	
	@Test
	public void testReplaceString() {
		String oldString = "# By default, if no \"bind\" configuration directive is specified, Redis listens\r\n" + 
				"# for connections from all the network interfaces available on the server.\r\n" + 
				"# It is possible to listen to just one or multiple selected interfaces using\r\n" + 
				"# the \"bind\" configuration directive, followed by one or more IP addresses.\r\n" + 
				"#\r\n" + 
				"# Examples:\r\n" + 
				"#\r\n" + 
				"# bind 192.168.1.100 10.0.0.1\r\n" + 
				"# bind 127.0.0.1 ::1\r\n" + 
				"#\r\n" + 
				"# ~~~ WARNING ~~~ If the computer running Redis is directly exposed to the\r\n" + 
				"# internet, binding to all the interfaces is dangerous and will expose the\r\n" + 
				"# instance to everybody on the internet. So by default we uncomment the\r\n" + 
				"# following bind directive, that will force Redis to listen only into\r\n" + 
				"# the IPv4 lookback interface address (this means Redis will be able to\r\n" + 
				"# accept connections only from clients running into the same computer it\r\n" + 
				"# is running).\r\n" + 
				"#\r\n" + 
				"# IF YOU ARE SURE YOU WANT YOUR INSTANCE TO LISTEN TO ALL THE INTERFACES\r\n" + 
				"# JUST COMMENT THE FOLLOWING LINE.";
		
		String newString = oldString.replace(OLD_KEY, NEW_KEY).replace("\r\n", " ");
		
		logger.debug("替换后的新字符串为：{}", newString);
		
		
	}

}
