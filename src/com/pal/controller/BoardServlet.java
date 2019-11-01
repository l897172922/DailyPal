package com.pal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pal.entity.Messages;
import com.pal.entity.Page;
import com.pal.entity.Users;
import com.pal.service.MessagesService;
import com.pal.service.UsersService;

public class BoardServlet extends HttpServlet {
	MessagesService mesSer = new MessagesService();
	UsersService userSer = new UsersService();
	/**
	 * Constructor of the object.
	 */
	public BoardServlet() {
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
		}else if("del".equals(op)){
			this.del(request, response);
		}
	}
	
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int m_id = 0;
		String mId = request.getParameter("m_id");
		if(mId!=null && !"".equals(mId)){
			m_id = Integer.parseInt(mId);
		}
		
		if(mesSer.delMessages(m_id)){
			response.getWriter().println();
		}
	}
	
	/**
	 * 显示留言板
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * 
	 */
	public void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Users user1 =(Users) request.getSession().getAttribute("userinfo");
		if(user1==null){
			Gson gson = new Gson();
			String json = gson.toJson("请登录后重试");
			response.getWriter().println(json);
			return;
		}else{
		// 响应回去的是一个json格式的对象。
		response.setContentType("application/json;charset=UTF-8");
		int myId = user1.getU_id();
		int currPage = 1;
		int pageSize = 5;
//		获取当前页信息
		String strCurr = request.getParameter("currPage");
		if(strCurr!=null && !strCurr.equals("")){
			currPage = Integer.parseInt(strCurr);
		}
//		获取页面容量信息
		String strSize = request.getParameter("pageSize");
		if(strSize!=null && !strSize.equals("")){
			pageSize = Integer.parseInt(strSize);
		}
		
//		获取分页信息
		Page<Messages> page = mesSer.getPage( myId , currPage, pageSize);
		List<Messages> msg = page.getList();
		Users user = new Users();
		ArrayList listz = new ArrayList();
		ArrayList link = new ArrayList();
		
		for (Messages messages : msg) {
			user = messages.getFromUsers();
			link = new ArrayList();
			link.add(messages);
			link.add(user.getNickName());
			
			listz.add(link);	
		}
		
		List list = new ArrayList();
		list.add(page);
		list.add(listz);
		
		
//		ajax json
		Gson gson = new Gson();
		String jsonPage = gson.toJson(list);
		
		response.getWriter().println(jsonPage);
		}
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
