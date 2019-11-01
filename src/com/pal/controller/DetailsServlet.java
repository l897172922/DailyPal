package com.pal.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pal.dao.CommentsDao;
import com.pal.entity.Blogs;
import com.pal.entity.Comments;
import com.pal.entity.Inform;
import com.pal.entity.Messages;
import com.pal.entity.Page;
import com.pal.entity.Users;
import com.pal.service.BlogsService;
import com.pal.service.CommentsService;
import com.pal.service.InformService;
import com.pal.service.MessagesService;
import com.pal.service.UsersService;

public class DetailsServlet extends HttpServlet {
	BlogsService bs = new BlogsService();
	MessagesService ms = new MessagesService();
	UsersService us = new UsersService();
	CommentsService cs = new CommentsService();
	InformService is = new InformService();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public DetailsServlet() {
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
		} else if (op.equals("sendmessage")) {
			this.sendmessage(request, response);
		} else if (op.equals("like")) {
			this.like(request, response);
		} else if (op.equals("comments")) {
			this.comments(request, response);
		} else if (op.equals("querycomments")) {
			this.querycomments(request, response);
		} else if (op.equals("blogcomments")) {
			this.blogcomments(request, response);
		} else if (op.equals("bloglike")) {
			this.bloglike(request, response);
		} else if (op.equals("delblog")) {
			this.delblog(request, response);
		} else if (op.equals("manageblog")) {
			this.manageblog(request, response);
		} else if (op.equals("delcomment")) {
			this.delcomment(request, response);
		} else if (op.equals("inform")) {
			this.inform(request, response);
		}
	}

	/**
	 * 执行举报操作
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void inform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String strId = request.getParameter("userid");
		
		int id = 0;
		if (strId != null && !"".equals(strId)) {
			id = Integer.parseInt(strId);
		}
		String reason = request.getParameter("reason");
		Inform inform = new Inform();
		
		int reasonid = 0;
		if (reason.equals("低俗色情")) {
			reasonid = 1;
		} else if (reason.equals("暴力")) {
			reasonid = 2;
		} else if (reason.equals("传销")) {
			reasonid = 3;
		} else if (reason.equals("其他")) {
			reasonid = 4;
		} else if (reason.equals("政治敏感")) {
			reasonid = 5;
		}

		java.util.Date date = new java.util.Date();
		java.sql.Date sqldate = new java.sql.Date(date.getTime());
		inform.setRt_typeId(reasonid);
		inform.setU_id(id);
		inform.setTime(sqldate);
		is.addInform(inform);
		request.setAttribute("banmsg", "举报成功");
		request.getRequestDispatcher("DetailsServlet?op=query&userid=" + id)
				.forward(request, response);
	}

	/**
	 * 执行删除我的动态
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void delblog(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String strId = request.getParameter("blogid");
		int id = 0;
		if (strId != null && !"".equals(strId)) {
			id = Integer.parseInt(strId);
		}
		bs.delBlogs(id);
		request.getRequestDispatcher("MyDynamicServlet?op=query").forward(
				request, response);
	}

	/**
	 * 执行删除我的动态的评论
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void delcomment(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// BlogsDao bd = new BlogsDao();
		String strId = request.getParameter("commentid");
		int id = 0;
		if (strId != null && !"".equals(strId)) {
			id = Integer.parseInt(strId);
		}
		
		Comments comment = cs.getComments(id);
		Blogs blog = bs.getBlogs(comment.getToblogsId());
		cs.delComments(id);

		
		request.getRequestDispatcher(
				"DetailsServlet?op=manageblog&blogid=" + blog.getB_id())
				.forward(request, response);
	}

	/**
	 * 动态详细显示评论
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void querycomments(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Users user =(Users) request.getSession().getAttribute("userinfo");
		/*
		 * if(user==null){ request.setAttribute("namemsg", "请先登录");
		 * request.getRequestDispatcher("login.jsp").forward(request, response);
		 * }else{
		 */
		String strId = request.getParameter("blogid");// 所点详情动态的id
		int blogid = 0;
		// System.out.println(strId);
		if (strId != null && !"".equals(strId)) {
			blogid = Integer.parseInt(strId);
		}
		Blogs blog = bs.getBlogs(blogid);
		Users user = us.getUser(blog.getUserId());
		// System.out.println(user);
		ArrayList<Comments> cmlist = cs.getCommentsByblogId(blog);
		request.setAttribute("blog", blog);
		request.setAttribute("cmlist", cmlist);
		request.setAttribute("user", user);
		request.getRequestDispatcher("blog.jsp").forward(request, response);

	}

	/**
	 * 管理动态评论
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void manageblog(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Users user =(Users) request.getSession().getAttribute("userinfo");
		/*
		 * if(user==null){ request.setAttribute("namemsg", "请先登录");
		 * request.getRequestDispatcher("login.jsp").forward(request, response);
		 * }else{
		 */
		String strId = request.getParameter("blogid");// 所点详情动态的id
		int blogid = 0;
		// System.out.println(strId);
		if (strId != null && !"".equals(strId)) {
			blogid = Integer.parseInt(strId);
		}

		Blogs blog = bs.getBlogs(blogid);
		Users user = us.getUser(blog.getUserId());
		// System.out.println(user);
		ArrayList<Comments> cmlist = cs.getCommentsByblogId(blog);
		request.setAttribute("blog", blog);
		request.setAttribute("cmlist", cmlist);
		request.setAttribute("user", user);
		request.getRequestDispatcher("blogmanage.jsp").forward(request,
				response);

	}

	// }

	/**
	 * 实现评论动态功能
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void comments(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Users user = (Users) request.getSession().getAttribute("userinfo");
		int blogid = 0;
		String strId = request.getParameter("blogid");// 被点赞动态的id
		// System.out.println(strId);
		if (strId != null && !"".equals(strId)) {
			blogid = Integer.parseInt(strId);
		}
		Blogs blog = bs.getBlogs(blogid);

		if (user == null) {
			request.setAttribute("namemsg", "请先登录");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		} else if (user.getIsban() == 1) {
			request.setAttribute("banmsg", "你的账号已被封禁，无法评论");
			request.getRequestDispatcher(
					"DetailsServlet?op=query&userid=" + blog.getUserId())
					.forward(request, response);
		} else {

			String content = request.getParameter("commentcontent");

			java.util.Date date = new java.util.Date();
			java.sql.Date sqldate = new java.sql.Date(date.getTime());
			Comments comment = new Comments();
			comment.setToblogsId(blogid);
			comment.setContent(content);
			comment.setFromuserId(user.getU_id());
			comment.setTime(sqldate);
			cs.addComments(comment);
			
			// System.out.println(blog.getCommentCounts());
			blog.setCommentCounts(blog.getCommentCounts() + 1);
			bs.addBlogcomment(blog);
			// System.out.println(blog.getCommentCounts());
			request.getRequestDispatcher(
					"DetailsServlet?op=querycomments&userid="
							+ blog.getUserId()).forward(request, response);
			// System.out.println(content);
		}
	}

	/**
	 * 实现评论详情动态功能
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void blogcomments(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Users user = (Users) request.getSession().getAttribute("userinfo");
		int blogid = 0;
		String strId = request.getParameter("blogid");// 被评论动态的id
		// System.out.println(strId);
		if (strId != null && !"".equals(strId)) {
			blogid = Integer.parseInt(strId);
		}
		Blogs blog = bs.getBlogs(blogid);
		if (user == null) {
			request.setAttribute("namemsg", "请先登录");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		} else if (user.getIsban() == 1) {
			request.setAttribute("banmsg", "你的账号已被封禁，无法评论");
			request.getRequestDispatcher(
					"DetailsServlet?op=querycomments&userid="
							+ blog.getUserId()).forward(request, response);
		} else {
			String content = request.getParameter("commentcontent");

			java.util.Date date = new java.util.Date();
			java.sql.Date sqldate = new java.sql.Date(date.getTime());
			Comments comment = new Comments();
			comment.setToblogsId(blogid);
			comment.setContent(content);
			comment.setFromuserId(user.getU_id());
			comment.setTime(sqldate);
			cs.addComments(comment);
			// Blogs blog = bs.getBlogs(blogid);
			// System.out.println(blog.getCommentCounts());
			blog.setCommentCounts(blog.getCommentCounts() + 1);
			bs.addBlogcomment(blog);
			// System.out.println(blog.getCommentCounts());
			request.getRequestDispatcher(
					"DetailsServlet?op=querycomments&blogid=" + blog.getB_id())
					.forward(request, response);
			// System.out.println(content);
		}
	}

	/**
	 * 执行动态点赞功能
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void like(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Users user = (Users) request.getSession().getAttribute("userinfo");
		int blogid = 0;

		String strId = request.getParameter("blogid");// 被点赞动态的id
		// System.out.println(strId);
		if (strId != null && !"".equals(strId)) {
			blogid = Integer.parseInt(strId);
		}
		// System.out.println(toid);
		Blogs blog = bs.getBlogs(blogid);
		if (user == null) {
			request.setAttribute("namemsg", "请先登录");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		} else if (user.getIsban() == 1) {
			request.setAttribute("banmsg", "你的账号已被封禁，无法点赞");
			request.getRequestDispatcher(
					"DetailsServlet?op=query&userid=" + blog.getUserId()).forward(request, response);
		} 
		else {	
			// System.out.println(blog);
			blog.setLikeCounts(blog.getLikeCounts() + 1);
			bs.addBloglike(blog);
			request.getRequestDispatcher(
					"DetailsServlet?op=query&userid=" + blog.getUserId())
					.forward(request, response);
			// System.out.println(content);
		}
	}

	/**
	 * 执行动态详情点赞功能
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void bloglike(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Users user = (Users) request.getSession().getAttribute("userinfo");
		int blogid = 0;

		String strId = request.getParameter("blogid");// 被点赞动态的id
		// System.out.println(strId);
		if (strId != null && !"".equals(strId)) {
			blogid = Integer.parseInt(strId);
		}
		// System.out.println(toid);
		Blogs blog = bs.getBlogs(blogid);
		if (user == null) {
			request.setAttribute("namemsg", "请先登录");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		} else if (user.getIsban() == 1) {
			request.setAttribute("banmsg", "你的账号已被封禁，无法点赞");
			request.getRequestDispatcher(
					"DetailsServlet?op=querycomments&blogid=" + blog.getB_id()).forward(request, response);
		} else {	
			// System.out.println(blog);
			blog.setLikeCounts(blog.getLikeCounts() + 1);
			bs.addBloglike(blog);
			request.getRequestDispatcher(
					"DetailsServlet?op=querycomments&blogid=" + blog.getB_id())
					.forward(request, response);
			// System.out.println(content);
		}
	}

	/**
	 * 执行留言操作
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void sendmessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Users user = (Users) request.getSession().getAttribute("userinfo");

		if (user == null) {
			request.setAttribute("namemsg", "请先登录");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		} else if (user.getIsban() == 1) {
			request.setAttribute("banmsg", "你的账号已被封禁，无法留言");
			request.getRequestDispatcher(
					"DetailsServlet?op=query").forward(request, response);
		} else {

			int toid = 0;
			String content = request.getParameter("massagecontent");
			if (content == null && "".equals(content)) {
				request.setAttribute("nullmsg", "不能为空");
				request.getRequestDispatcher("DetailsServlet?op=query")
						.forward(request, response);
			} else {
				String strId = request.getParameter("userid");// 被留言人的id
				// System.out.println(strId);
				if (strId != null && !"".equals(strId)) {
					toid = Integer.parseInt(strId);
				}
				// System.out.println(toid);
				int fromuserid = user.getU_id();

				java.util.Date date = new java.util.Date();
				java.sql.Date sqldate = new java.sql.Date(date.getTime());

				Messages message = new Messages();
				message.setFromuserId(fromuserid);
				message.setTouserId(toid);
				message.setContent(content);
				message.setTime(sqldate);
				ms.addMessages(message);
				request.getRequestDispatcher("DetailsServlet?op=query")
						.forward(request, response);
			}

			// System.out.println(content);
		}
	}

	/**
	 * 执行查询留言，动态，个人信息操作
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = 0;
		int currPage = 1;
		int pageSize = 10;
		String strId = request.getParameter("userid");
		if (strId != null && !"".equals(strId)) {
			id = Integer.parseInt(strId);
		}

		Page<Messages> mpage = ms.getPage(id, currPage, pageSize);
		Page<Blogs> bpage = bs.getPage(id, currPage, pageSize);
		Users user = us.getUser(id);
		// System.out.println(user);
		ArrayList<Messages> mlist = (ArrayList<Messages>) mpage.getList();
		ArrayList<Blogs> blist = (ArrayList<Blogs>) bpage.getList();
		request.setAttribute("mlist", mlist);
		request.setAttribute("mpage", mpage);
		request.setAttribute("blist", blist);
		request.setAttribute("bpage", bpage);
		request.setAttribute("user", user);
		request.getRequestDispatcher("details.jsp").forward(request, response);
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
