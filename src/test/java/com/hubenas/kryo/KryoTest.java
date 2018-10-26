package com.hubenas.kryo;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubenas.entity.MessageContent;
import com.hubenas.entity.PersonAImpl;
import com.hubenas.kryo.util.KryoUtils;

/**
 * 测试Kryo
 *
 * @author 曾谢波
 * @since 2018年10月18日
 */
public class KryoTest {
	private static final Logger logger = LoggerFactory.getLogger(KryoTest.class);
	@Test
	public void testKryo() {
		PersonAImpl personAImpl = new PersonAImpl();
		personAImpl.setAge(33);
		personAImpl.setName("小明");
		
		MessageContent<PersonAImpl> messageContent = new MessageContent<>();
		messageContent.setAddress("测试地址");
		messageContent.setData(personAImpl);
		
		byte[] bytes = KryoUtils.serialize(messageContent);
		logger.debug("序列化后的类bytes：{},转换为string后：{}", bytes, new String(bytes));
		MessageContent<PersonAImpl> messageContent2 = (MessageContent<PersonAImpl>) KryoUtils.deserialize(bytes);
		
		logger.debug("反序列化后的类：{}", messageContent2.toString());
	}
}
