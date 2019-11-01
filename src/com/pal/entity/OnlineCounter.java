package com.pal.entity;



public class OnlineCounter {
	
	private static long onlineCount=0;//当前在线人数
	private static long accessCount=0;//网站总访问量
	private static long registerCount=0;//当前注册人数
	
	public static long getOnlineCount(){
		return onlineCount;
	}
	
	public static long getRegisterCount(){
		return registerCount;
	}
	
	public static long getAccessCount(){
		return accessCount;
	}
	
	/*public  static void raise(){
			onlineCount++;
	}
	
	public static void reduce(){
		
		onlineCount--;
		
	}*/
	public  static void accessCountRaise(){
		accessCount++;
	}
	
	
}
