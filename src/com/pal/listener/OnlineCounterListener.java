package com.pal.listener;



import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.pal.entity.OnlineCounter;

public class OnlineCounterListener implements HttpSessionListener {
	
	
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		//统计当前在线人数
		//OnlineCounter.raise();
		
		OnlineCounter.accessCountRaise();
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		//OnlineCounter.reduce();
	}

}
