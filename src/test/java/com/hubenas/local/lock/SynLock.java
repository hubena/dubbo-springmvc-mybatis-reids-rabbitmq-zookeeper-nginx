package com.hubenas.local.lock;

/**
 * <br>用作锁对象. 
 * 
 * @author 曾谢波
 * @since 2018年9月29日
 */
public class SynLock {
	public String name;
	public Integer age;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	
	@Override
	public String toString() {
		return "SynLock [name=" + name + ", age=" + age + "]";
	}
	
	
	
}
