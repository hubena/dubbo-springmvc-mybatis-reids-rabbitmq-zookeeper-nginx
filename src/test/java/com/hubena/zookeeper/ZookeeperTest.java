package com.hubena.zookeeper;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.naming.event.NamespaceChangeListener;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.server.auth.AuthenticationProvider;
import org.apache.zookeeper.server.auth.IPAuthenticationProvider;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.AMQP.Channel;

public class ZookeeperTest {
	private static final Logger logger = LoggerFactory.getLogger(ZookeeperTest.class);
	private static final String connectString = "127.0.0.1:2183,127.0.0.1:2181,127.0.0.1:2182";
	private static final int sessionTimeout = 500000000;
	private static ZooKeeper zooKeeper;
	private static Watcher watcher = new Watcher() {
	
		@Override
		public void process(WatchedEvent event) {
			logger.error("监听到的事件为watchedEvent:{},path:{},type{}", 
					event, event.getPath(), event.getType());
		}
	};
	
	@BeforeClass
	public static void beforeTest() throws IOException, KeeperException, InterruptedException {
		zooKeeper = new ZooKeeper(connectString, sessionTimeout, watcher);
		logger.error("获取到的zookeeper连接:{}", zooKeeper);
//		zooKeeper.create("/root", new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	}
	
//	@Test
	public void testZookeeper() throws IOException, KeeperException, InterruptedException {
		byte[] data = zooKeeper.getData("/z_nodeTest2", watcher, null);
		logger.error("取到的Data值:{}", new String(data));
		
		
		AuthenticationProvider authenticationProvider = new IPAuthenticationProvider();
		logger.error("authenticationProvider.getScheme():{}", authenticationProvider.getScheme());
//		TimeUnit.SECONDS.sleep(1000000);
		zooKeeper.close();
	}
	
	@Test
	public void testSequenceZnode() throws KeeperException, InterruptedException {
		String dataValue = "节点data";
		byte[] dataByte = dataValue.getBytes();
		
		String actualPath = zooKeeper.create("/root/Sequential_node" + Calendar.getInstance().getTime().toString(), dataByte, 
			Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
		logger.error("----actualPath:{}", actualPath);
		List<String> list = zooKeeper.getChildren("/root", true);
		for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			logger.error(string);
		}
		String data = new String(zooKeeper.getData("/root", true, null));
		logger.error(data);
		TimeUnit.SECONDS.sleep(5000000);
	}
}
