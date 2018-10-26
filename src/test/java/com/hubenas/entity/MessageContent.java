package com.hubenas.entity;

public class MessageContent<T> {
	/**
	 * 地址.
	 */
	private String address;
	
	/**
	 * 要传输的数据.
	 */
	private T data;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "MessageContent [address=" + address + ", data=" + data + "]";
	}
}
