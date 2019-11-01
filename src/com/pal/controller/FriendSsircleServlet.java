package com.pal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pal.dao.BlogsDao;
import com.pal.dao.FriendDao;
import com.pal.entity.Blogs;
import com.pal.entity.Friend;
import com.pal.entity.Users;
import com.pal.service.BlogsService;
import com.pal.service.CommentsService;
import com.pal.service.FriendService;
import com.pal.service.MessagesService;
import com.pal.service.UsersService;

public class FriendSsircleServlet extends HttpServlet {
	BlogsService bs = new BlogsService();
	MessagesService ms = new MessagesService();
	UsersService us = new UsersService();
	CommentsService cs = new CommentsService();
	FriendService fs = new FriendService();


	/**
	 * Constructor of the object.
	 */
	public FriendSsircleServlet() {
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
		if (op.equals("query")) {
			this.query(request, response);
		}else if(op.equals("bloglike")){
			this.bloglike(request, response);
		}
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Users user =(Users) request.getSession().getAttribute("userinfo");
		if(user==null){
			request.setAttribute("namemsg", "请先登录");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else{
		ArrayList<Friend> flist = fs.getAllFriend(user.getU_id());
		ArrayList list = new ArrayList();
		for (Friend l : flist) {
			if (l.geTotUsers().getU_id() == 1) {
				list.add(l.getFromuserId());
			} else {
				list.add(l.getTouserId());
			}
		}
		ArrayList<Blogs> blist = new ArrayList<Blogs>();
		for (int i = 0; i < list.size(); i++) {
			blist.addAll(bs.getAllBlogs((int) list.get(i)));
		}
		request.setAttribute("user", user);
		request.setAttribute("bloglist", blist);
		request.getRequestDispatcher("friendssircle.jsp").forward(request, response);
		}
	}
	/**
	 * 执行动态详情点赞功能
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void bloglike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Users user =(Users) request.getSession().getAttribute("userinfo");
		
		if(user==null){
			request.setAttribute("namemsg", "请先登录");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else{
			
			int blogid =0;
			
			String strId = request.getParameter("blogid");//被点赞动态的id
			//System.out.println(strId);
			if (strId != null && !"".equals(strId)) {
				blogid = Integer.parseInt(strId);
			}
			//System.out.println(toid);
			Blogs blog = bs.getBlogs(blogid);
			//System.out.println(blog);
			blog.setLikeCounts(blog.getLikeCounts()+1);
			bs.addBloglike(blog);
			request.getRequestDispatcher("FriendSsircleServlet?op=query").forward(request, response);
			//System.out.println(content);
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
