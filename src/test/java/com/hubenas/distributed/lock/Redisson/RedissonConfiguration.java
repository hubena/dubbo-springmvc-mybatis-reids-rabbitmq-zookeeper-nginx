package com.hubenas.distributed.lock.Redisson;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfiguration {
	
	@Bean
	public Redisson getRedisson() {
//		Config config = new Config();
//		config.useClusterServers().setScanInterval(3000)  // 集群状态扫描间隔时间，单位是毫秒
//		//可以用"rediss://"来启用SSL连接
//		.addNodeAddress("192.168.0.5:6379");
		
		
		// 单节点配置
//		Config config = new Config();
//		config.useSingleServer().setAddress("127.0.0.1:6379");
		return (Redisson) Redisson.create();
	}
}
