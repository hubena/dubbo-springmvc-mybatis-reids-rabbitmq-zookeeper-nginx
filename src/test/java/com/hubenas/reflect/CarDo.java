package com.hubenas.reflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarDo {
	private static final Logger logger = LoggerFactory.getLogger(CarDo.class);
	
	private String colour = "red";
	private int weitht = 33;
	private static int weight2 = 33 + 1;
	
	static {
		logger.error("执行static块了");
	}
	
	public CarDo() {
		logger.error("执行CarDo构造方法了");
	}
}
