package com.pal.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pal.entity.FriendRequest;
import com.pal.entity.Users;
import com.pal.service.FriendRequestService;
import com.pal.service.UsersService;

public class UserServlet extends HttpServlet {
	UsersService userSer = new UsersService();
	FriendRequestService frSer = new FriendRequestService();

	/**
	 * Constructor of the object.
	 */
	public UserServlet() {
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
		this.doPost(request, response);
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
		String op = request.getParameter("op");

		if (op.equals("login")) {
			this.login(request, response);
		} else if (op.equals("register")) {
			this.register(request, response);
		} else if (op.equals("add")) {
			this.add(request, response);
		}
	}

	/**
	 * 加好友
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int friId = 0;
		Users u = (Users) request.getSession().getAttribute("userinfo");
		if (u == null) {
			Gson gson = new Gson();
			String json = gson.toJson("请登录后重试");
			response.getWriter().println(json);
			return;
		}else{
		int myId = u.getU_id();
		// 获取要加的好友id
		String fId = request.getParameter("FriId");
		if (fId != null && !"".equals(fId)) {
			friId = Integer.parseInt(fId);
		}
		
		java.util.Date d = new java.util.Date();
		java.sql.Date date = new java.sql.Date(d.getTime());

		FriendRequest fr = new FriendRequest();
		fr.setFromuserId(myId);
		fr.setTouserId(friId);
		fr.setSendTime(date);
		fr.setReadState("0");
		

		if (frSer.addFriendRequest(fr)) {
			Gson gson = new Gson();
			String json = gson.toJson("消息发送成功");
			response.getWriter().println(json);
		}}

	}

	/**
	 * 登陆
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取用户名密码
		String UserName = request.getParameter("UserName");
		String UserPwd = request.getParameter("Password");
		String remenber = request.getParameter("remenber");


		// 查找用户名是否存在
		Users user = new Users();
		user = userSer.getUser(UserName);
		// 判断是否存在
		if (user == null) {
			request.setAttribute("namemsg", "用户名不存在");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		} else {

			// 如果存在判断密码是否正确
			if (UserPwd.equals(user.getU_pwd())) {
				if (remenber != null && "true".equals(remenber)) {
					Cookie name = new Cookie("UserName", UserName);
					Cookie pwd = new Cookie("UserPwd", UserPwd);
					name.setMaxAge(60 * 60 * 24 * 7);
					pwd.setMaxAge(60 * 60 * 24);
					response.addCookie(name);
					response.addCookie(pwd);
					request.getSession().setAttribute("userinfo", user);
					response.sendRedirect("index.jsp");
				} else if (remenber == null) {
					Cookie[] cookies = request.getCookies();

					for (Cookie cookie : cookies) {

						cookie.setMaxAge(0);

						response.addCookie(cookie);
					}
					request.getSession().setAttribute("userinfo", user);
					response.sendRedirect("index.jsp");
				}
			} else {
				request.setAttribute("pwdmsg", "密码错误");
				request.getRequestDispatcher("login.jsp").forward(request,
						response);
			}
		}
	}

	/**
	 * 注册
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void register(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		// 获取用户名、密码、确认密码
		String UserName = request.getParameter("username");
		String UserPwd = request.getParameter("password");
		String repeatPassword = request.getParameter("repeatPassword");

		// 判断用户是否存在
		Users users = new Users();
		users = userSer.getUser(UserName);
		if (users != null) {

			request.setAttribute("regi", "用户名已存在");
			request.getRequestDispatcher("register.jsp").forward(request,
					response);
			return;

		} else {
			if (UserName.length() < 5 || UserName.length() > 12) {
				request.setAttribute("regi", "用户名长度为5-12位之间");
				request.getRequestDispatcher("register.jsp").forward(request,
						response);
				return;
			} else {
				request.setAttribute("regi", "用户名可用");
			}
		}

		if (UserPwd.length() != 0) {
			if (UserPwd.length() < 6 || UserPwd.length() > 16) {
				request.setAttribute("regipwd", "密码长度为6-16位之间");
				request.getRequestDispatcher("register.jsp").forward(request,
						response);
				return;
			} else {
				request.setAttribute("regipwd", "密码可用");
			}

		}

		if (repeatPassword.length() != 0) {
			if (repeatPassword.length() < 6 || repeatPassword.length() > 16) {

				request.setAttribute("regipwd1", "确认密码长度为6-16位之间");
				request.getRequestDispatcher("register.jsp").forward(request,
						response);
				return;
			} else if (!repeatPassword.equals(UserPwd)) {
				request.setAttribute("regipwd1", "确认密码与第一次密码不一致");
				request.getRequestDispatcher("register.jsp").forward(request,
						response);
				return;
			} else {
				request.setAttribute("regipwd1", "确认密码正确");
			}

		}

		// 获取当前时间
		java.util.Date date = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		users = new Users();
		users.setU_name(UserName);
		users.setU_pwd(UserPwd);
		// 默认头像
		users.setHeadPic("t0a08f3a4d1d04333973b2155992bd800.gif");
		// 昵称默认账号
		users.setNickName(UserName);
		// 性别默认未知
		users.setSex("未知");
		// 年龄默认0
		users.setAge(0);
		// 注册时间
		users.setRegisterTime(sqlDate);


		if (userSer.addUser(users)) {

			request.setAttribute("msg", "注册成功");
			response.sendRedirect("login.jsp");

		}

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
