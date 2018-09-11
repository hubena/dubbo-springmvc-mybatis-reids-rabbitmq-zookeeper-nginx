package com.hubena.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import com.hubena.entity.IPerson;
import com.hubena.entity.PersonAImpl;

@Component(value = "personFactoryBean")
public class PersonFactoryBean implements FactoryBean<IPerson> {
	private static final Logger logger = LoggerFactory.getLogger(PersonFactoryBean.class);
	
	private PersonAImpl personA;
	private IPerson proxyObject;
	
	
	/**
	 * {@inheritDoc}<P>
	 * 返回需要的类,并为其生成代理。
	 */
	@Override
	public IPerson getObject() throws Exception {
		personA = new PersonAImpl();
		IPerson proxyObject = (IPerson) Proxy.newProxyInstance(personA.getClass().getClassLoader(), personA.getClass().getInterfaces(), new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				logger.error("proxy参数：{},method参数{}", proxy.getClass(), method.getName());
				Object result = method.invoke(personA, args);
				return result;
			}
		});
		return proxyObject;
	}

	@Override
	public Class<?> getObjectType() {
		return proxyObject == null ? Object.class : proxyObject.getClass();
	}
	
	@Override
	public boolean isSingleton() {
		return false;
	}

}
