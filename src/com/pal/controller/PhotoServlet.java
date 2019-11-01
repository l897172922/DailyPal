package com.pal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class PhotoServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PhotoServlet() {
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
		// 获取文本框内容
//		String desc = request.getParameter("desc");
		// 获取要上传的文件
		Part part = request.getPart("file");
		// 获取请求信息
		String name = part.getHeader("content-disposition");

		// 上传路径
		String tempPath = "/headpic";
		//System.out.println("设置的上传路径" + tempPath);

		// 获取上传文件的目录
		String root = request.getServletContext().getRealPath(tempPath);
		//System.out.println("上传的目录" + root);

		// 获取文件后缀
		String suffix = name
				.substring(name.lastIndexOf("."), name.length() - 1);

		// UUID生成新文件名
		String filePath = UUID.randomUUID().toString() + suffix;
		//System.out.println("UUID随机" + filePath);

		request.setAttribute("file", filePath);

		String filename = root + "\\" + filePath;

		// 上传到服务器
		part.write(filename);
		request.setAttribute("info", "上传成功");

		request.getRequestDispatcher("up.jsp").forward(request, response);

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
