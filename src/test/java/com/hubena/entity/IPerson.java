package com.hubena.entity;

public interface IPerson {
	
	public default void eatSome() {
		System.out.println("名字为Person的人开始吃东西了!");
	}
}
