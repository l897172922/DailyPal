package com.pal.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pal.entity.Blogs;
import com.pal.entity.Messages;
import com.pal.entity.Page;
import com.pal.entity.Users;
import com.pal.service.BlogsService;

public class MyDynamicServlet extends HttpServlet {
	BlogsService blogSer = new BlogsService();

	/**
	 * Constructor of the object.
	 */
	public MyDynamicServlet() {
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
			this.quety(request, response);
		}
	}
	
	public void quety(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		/*
		// 响应回去的是一个json格式的对象。
		response.setContentType("application/json;charset=UTF-8");*/
		
		Users user =(Users) request.getSession().getAttribute("userinfo");
		if(user==null){
			request.setAttribute("namemsg", "请先登录");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else{
		int myId = user.getU_id();
		int currPage = 1;
		int pageSize = 100;
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
		Page<Blogs> page = blogSer.getPage(myId, currPage, pageSize);
		
		request.setAttribute("page", page);
		request.setAttribute("list", page.getList());
		request.getRequestDispatcher("mydynamic.jsp").forward(request, response);
		
		}
/*//		ajax json
		Gson gson = new Gson();
		String jsonPage = gson.toJson(page);
		
		response.getWriter().println(jsonPage);*/
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
