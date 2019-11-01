package com.pal.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineCountListener implements HttpSessionListener {
	private static Integer numSessions=0;
	/*@SuppressWarnings("unchecked")*/
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		ServletContext ctx =arg0.getSession().getServletContext();
		/*Integer numSessions=(Integer)ctx.getAttribute("numSessions");*/
		numSessions=(Integer)ctx.getAttribute("numSessions");
		
		if(numSessions==null){
			numSessions=new Integer(1);
		}else{
			int count =numSessions.intValue();
			numSessions=new Integer(count+1);
		}
		ctx.setAttribute("numSessions", numSessions);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		ServletContext ctx =arg0.getSession().getServletContext();
		Integer numSessions=(Integer)ctx.getAttribute("numSessions");
		
		if(numSessions==null){
			numSessions=new Integer(0);
		}else{
			int count =numSessions.intValue();
			numSessions=new Integer(count-1);
			if(count<=0){
				numSessions=0;
			}
		}
		ctx.setAttribute("numSessions", numSessions);
	}

}
