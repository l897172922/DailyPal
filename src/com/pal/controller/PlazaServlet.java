package com.pal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.pal.entity.Blogs;
import com.pal.entity.Users;
import com.pal.service.BlogsService;
import com.pal.service.UsersService;

@MultipartConfig
public class PlazaServlet extends HttpServlet {
	Blogs blog = new Blogs();
	BlogsService blogSer = new BlogsService();
	UsersService userSer = new UsersService();

	/**
	 * Constructor of the object.
	 */
	public PlazaServlet() {
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
		} else if ("release".equals(op)) {
			this.release(request, response);
		} else if ("releaseCon".equals(op)){
			this.releaseCon(request, response);
		}

	}
	
	/**
	 * 无图发表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void releaseCon(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Users user = new Users();
		user = (Users)request.getSession().getAttribute("userinfo");
		
		String context = request.getParameter("text");
		
		java.util.Date d = new java.util.Date();
		java.sql.Date date = new java.sql.Date(d.getTime());
		
		blog.setUserId(user.getU_id());
		blog.setText(context);
		blog.setSendTime(date);
		blog.setLikeCounts(0);
		blog.setCommentCounts(0);
		if(blogSer.addBlogs(blog)){
			response.getWriter().println();
		}
		
	}
	

	/**
	 * 发布动态
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void release(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 响应回去的是一个json格式的对象。
		response.setContentType("application/json;charset=UTF-8");
		java.util.Date d = new java.util.Date();
		java.sql.Date date = new java.sql.Date(d.getTime());
		
		Users user = new Users();
		user = (Users)request.getSession().getAttribute("userinfo");
		
		
		// 获取要上传的文件
		Part part = request.getPart("file");
		
		// 获取请求信息
		String name = part.getHeader("content-disposition");

		// 上传路径
		String tempPath = "/front/detailpic";
		// 获取上传文件的目录
		String root = request.getServletContext().getRealPath(tempPath);

		// 获取文件后缀
		String suffix = name
				.substring(name.lastIndexOf("."), name.length() - 1);

		// UUID生成新文件名
		String filePath = "t" + UUID.randomUUID().toString().replace("-", "")
				+ suffix;
		String filename = root + "\\" + filePath;
		// 上传到服务器
		part.write(filename);
		
//		获取文字信息
		String context = request.getParameter("text");	
		
		blog.setUserId(user.getU_id());
		blog.setPicturePath(filePath);
		blog.setText(context);
		blog.setSendTime(date);
		blog.setLikeCounts(0);
		blog.setCommentCounts(0);
		if(blogSer.addBlogs(blog)){
			response.getWriter().println();
		}
		//request.getRequestDispatcher("release.jsp").forward(request, response);
	}

	/**
	 * 获取广场信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = 0;
		Users user = userSer.getUser(id);

		ArrayList<Blogs> blogList = blogSer.getHotBlogs();
	

		request.setAttribute("blogList", blogList);
		request.getRequestDispatcher("plaza.jsp").forward(request, response);

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
