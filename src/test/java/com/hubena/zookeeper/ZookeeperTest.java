package com.hubena.zookeeper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.server.auth.AuthenticationProvider;
import org.apache.zookeeper.server.auth.IPAuthenticationProvider;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZookeeperTest {
	private static final Logger logger = LoggerFactory.getLogger(ZookeeperTest.class);
	private static final String connectString = "127.0.0.1:2183";
	private static final int sessionTimeout = 50000;
	private static Watcher watcher = new Watcher() {
		
		@Override
		public void process(WatchedEvent event) {
			logger.debug("监听到的事件为watchedEvent:{},path:{},type{}", 
					event, event.getPath(), event.getType());
		}
	};
	@Test
	public void testZookeeper() throws IOException, KeeperException, InterruptedException {
		ZooKeeper zooKeeper = new ZooKeeper(connectString, sessionTimeout, watcher);
		logger.debug("获取到的zookeeper连接:{}", zooKeeper);
		byte[] data = zooKeeper.getData("/z_nodeTest2", watcher, null);
		logger.debug("取到的Data值:{}", new String(data));
		
		
		AuthenticationProvider authenticationProvider = new IPAuthenticationProvider();
		logger.debug("authenticationProvider.getScheme():{}", authenticationProvider.getScheme());
		TimeUnit.SECONDS.sleep(1000000);
		zooKeeper.close();
	}
}
