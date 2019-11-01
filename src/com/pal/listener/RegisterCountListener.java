package com.pal.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class RegisterCountListener implements HttpSessionBindingListener {

	
	private String u_name;
	private String u_pwd;
	
	
	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public String getU_pwd() {
		return u_pwd;
	}

	public void setU_pwd(String u_pwd) {
		this.u_pwd = u_pwd;
	}

	public RegisterCountListener(String userId,String userName){
		
		this.setU_name(u_name);
		this.setU_pwd(u_pwd);
	}
	
	/*@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}*/

	/*@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub

	}*/
	@SuppressWarnings("unchecked")
	public void valueBound(HttpSessionBindingEvent event){
		HttpSession session=event.getSession();
		ServletContext application=session.getServletContext();
		
		
		// 把用户放入在线列表
	    Map onlineUserList = (Map) application.getAttribute("onlineUserList");
	    // 第一次使用前，需要初始化
	    if (onlineUserList == null) {
	        onlineUserList = new HashMap();
	        application.setAttribute("onlineUserList", onlineUserList);
	    }
	    onlineUserList.put(this.u_name, this.u_pwd);

	}
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
	    ServletContext application = session.getServletContext();

	    // 从在线列表中删除用户
	    Map onlineUserList = (Map) application.getAttribute("onlineUserList");
	    onlineUserList.remove(this.u_name);

		
	}

	
	
	
	
	
}





