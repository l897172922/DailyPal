package com.pal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pal.entity.Friend;
import com.pal.entity.Messages;
import com.pal.entity.Page;
import com.pal.entity.Users;
import com.pal.service.FriendService;

public class FriendListServlet extends HttpServlet {
	FriendService friSer = new FriendService();
	Friend friend = new Friend();

	/**
	 * Constructor of the object.
	 */
	public FriendListServlet() {
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
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("op");
		if ("query".equals(op)) {
			this.query(request, response);
		} else if ("send".equals(op)) {

		} else if ("del".equals(op)) {
			this.del(request, response);
		}
	}

	/**
	 * 删除好友
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 响应回去的是一个json格式的对象。
		response.setContentType("application/json;charset=UTF-8");

		Users u = (Users) request.getSession().getAttribute("userinfo");
		int myId = u.getU_id();
		int friId = 0;
		// 获取好友id
		String friid = request.getParameter("friId");
		if (friid != null) {
			friId = Integer.parseInt(friid);
		}

		if (friSer.delFriend(myId, friId)) {
			response.getWriter().println();
		}

	}

	/**
	 * 显示好友列表
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 响应回去的是一个json格式的对象。
		response.setContentType("application/json;charset=UTF-8");
		Users user = (Users) request.getSession().getAttribute("userinfo");
		Gson gson = new Gson();
		
		if (user == null) {
			
			String json = gson.toJson("请登录后重试");
			response.getWriter().println(json);
			return;
		} else {
			int myId = 1;
			int currPage = 1;
			int pageSize = 5;
			// 获取当前页信息
			String strCurr = request.getParameter("currPage");
			if (strCurr != null && !strCurr.equals("")) {
				currPage = Integer.parseInt(strCurr);
			}
			// 获取页面容量信息
			String strSize = request.getParameter("pageSize");
			if (strSize != null && !strSize.equals("")) {
				pageSize = Integer.parseInt(strSize);
			}

			// 获取sessionid

			myId = user.getU_id();

			// 获取分页信息
			Page<Friend> page = friSer.getPage(myId, currPage, pageSize);

			// 创建添加page，list（user.list）
			List allList = new ArrayList();

			// 从page中获取的所有user
			List<Friend> flist = page.getList();
			// 添加user
			List<Users> list = new ArrayList();
			// 循环page中的user 添加到list中
			for (Friend l : flist) {
				if (l.geTotUsers().getU_id() == myId) {
					list.add(l.geFromtUsers());
				} else {
					list.add(l.geTotUsers());
				}
			}
			allList.add(page);
			allList.add(list);
			// ajax json
			String jsonPage = gson.toJson(allList);
			response.getWriter().println(jsonPage);
		}
	}

	/**
	 * 发消息？
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void send(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
