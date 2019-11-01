package com.pal.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.pal.entity.Page;
import com.pal.entity.Pictures;
import com.pal.entity.Users;
import com.pal.service.PicturesService;

@MultipartConfig
public class PictureServlet extends HttpServlet {
	Pictures pic = new Pictures();
	PicturesService picSer = new PicturesService();

	/**
	 * Constructor of the object.
	 */
	public PictureServlet() {
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
		String id = request.getParameter("picId");
		if ("query".equals(op)) {
			this.query(request, response);
		} else if ("add".equals(op)) {
			this.add(request, response);
		} else if ("del".equals(op)) {
			this.del(request, response);
		}
	}

	/**
	 * 上传照片
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId = 0;
		// 响应回去的是一个json格式的对象。
		response.setContentType("application/json;charset=UTF-8");

		// 获取要上传的文件
		Part part = request.getPart("file");
		// 获取请求信息
		String name = part.getHeader("content-disposition");

		// 上传路径
		String tempPath = "/front/picture";

		// 获取上传文件的目录
		String root = request.getServletContext().getRealPath(tempPath);

		// 获取文件后缀
		String suffix = name
				.substring(name.lastIndexOf("."), name.length() - 1);
		// UUID生成新文件名
		String filePath = UUID.randomUUID().toString() + suffix;

		Users user = (Users)request.getSession().getAttribute("userinfo");
		userId = user.getU_id();
		
		java.util.Date date = new java.util.Date();
		java.sql.Date time = new java.sql.Date(date.getTime());
		
		pic.setUserId(userId);
		pic.setPath(filePath);
		pic.setTime(time);
		picSer.addPictures(pic);
		String filename = root + "\\" + filePath;
		// 上传到服务器
		part.write(filename);

		// ajax返回
		Gson gson = new Gson();
		String json = gson.toJson(filePath);
		response.getWriter().println(json);

	}

	/**
	 * 删除照片
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
		int picId = 0;
		
		String picid = request.getParameter("picId");
		if(picid!=null&&!"".equals(picid)){
			picId = Integer.parseInt(picid);
		}
		
		if(picSer.delPictures(picId)){
			response.getWriter().println();
			
			
		}
		

	}

	/**
	 * 查询所有照片
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 响应回去的是一个json格式的对象。
		Users user =(Users) request.getSession().getAttribute("userinfo");
		if(user==null){
			Gson gson = new Gson();
			String json = gson.toJson("请登录后重试");
			response.getWriter().println(json);
			return;
		}else{
		response.setContentType("application/json;charset=UTF-8");

		int userId = 0;
		int currPage = 1;
		int pageSize = 10;
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
		
	
		userId = user.getU_id();

		Page<Pictures> page = picSer.getPage(userId, currPage, pageSize);
		// ajax json
		Gson gson = new Gson();
		String json = gson.toJson(page);
		response.getWriter().println(json);
		}
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
