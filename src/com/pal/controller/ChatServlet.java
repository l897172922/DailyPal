package com.pal.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pal.entity.ChatMessages;
import com.pal.entity.Users;
import com.pal.service.ChatMessagesService;

public class ChatServlet extends HttpServlet {
	ChatMessages cm = new ChatMessages();
	ChatMessagesService cmSer = new ChatMessagesService();
	/**
	 * Constructor of the object.
	 */
	public ChatServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("op");
		if("query".equals(op)){
			this.query(request, response);
		}else if("send".equals(op)){
			this.send(request, response);
		}else if("readstate".equals(op)){
			this.readstate(request, response);
		}
	}
	
	/**
	 * 获取未读的消息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void readstate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 响应回去的是一个json格式的对象。
		response.setContentType("application/json;charset=UTF-8");
		ArrayList<ChatMessages> cmList = new ArrayList<ChatMessages>();
		int friId = 0;
		String friid = request.getParameter("friId");
		if(friid!=null && !"".equals(friid)){
			friId = Integer.parseInt(friid);
		}
		
		Users user = (Users)request.getSession().getAttribute("userinfo");
		int myId = user.getU_id();
		cmList = cmSer.getAllChatMessagesWN(myId, friId);
		for(ChatMessages cm:cmList){
			if(cmSer.modChatMessages(cm.getCmId())){
				Gson gson = new Gson();
				String json = gson.toJson(cmList);
				response.getWriter().println(json);
			}
		}
				
	}
	/**
	 * 发送消息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void send(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int friId = 0;
		String content = request.getParameter("text");
		String friid = request.getParameter("friId");
		if(friid!=null && !"".equals(friid)){
			friId = Integer.parseInt(friid);
		}
		Users user = (Users)request.getSession().getAttribute("userinfo");
		int myId = user.getU_id();
		
		java.util.Date d = new java.util.Date();
		java.sql.Date date = new java.sql.Date(d.getTime());
		
		cm.setFromuserId(myId);
		cm.setTouserId(friId);
		cm.setSendTime(date);
		cm.setContent(content);
		cm.setReadstate("0");
		
		if(cmSer.addChatMessages(cm)){
			response.getWriter().println();
		}
	}
	
	/**
	 * 查询所有聊天信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 响应回去的是一个json格式的对象。
		response.setContentType("application/json;charset=UTF-8");
				
		ArrayList<ChatMessages> cmList = new ArrayList<ChatMessages>();
		int friId = 0;
		String friid = request.getParameter("friId");
		if(friid!=null && !"".equals(friid)){
			friId = Integer.parseInt(friid);
		}
		
		Users user = (Users)request.getSession().getAttribute("userinfo");
		int myId = user.getU_id();
		cmList = cmSer.getAllChatMessagesW(myId, friId);
		for(ChatMessages cm:cmList){
			cmSer.modChatMessages(cm.getCmId());
			
		}
		Gson gson = new Gson();
		String json = gson.toJson(cmList);
		response.getWriter().println(json);
		
		
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
