package com.pal.controller;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.pal.entity.Users;
import com.pal.service.UsersService;

@MultipartConfig
public class MyCenterServlet extends HttpServlet {
	Users user = new Users();
	UsersService userSer = new UsersService();

	/**
	 * Constructor of the object.
	 */
	public MyCenterServlet() {
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
		} else if ("submit".equals(op)) {
			this.submit(request, response);
		} else if ("head".equals(op)) {
			this.head(request, response);
		}
	}

	/**
	 * 显示个人资料
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] addr = new String[] { "", "" };
		String[] hobby = new String[] {};
		int userId = 1;
//		获取session中的user
		Users loginUser = new Users();
		loginUser = (Users)request.getSession().getAttribute("userinfo");
		if(loginUser==null){
			request.setAttribute("namemsg", "请先登录");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}else{
		userId = loginUser.getU_id();
//		通过登陆者ID获取信息
		user = userSer.getUser(userId);
//		获取地址
		String address = user.getAddress();
//		切割地址
		if (address != null && !"".equals(address)) {
			addr = address.split(" ");
		}
//		获取爱好
		String hobbys = user.getHobby();
//		切割爱好
		if (hobbys != null && !"".equals(hobbys)) {
			hobby = hobbys.split(",");
			request.setAttribute("hobby", hobby);
		}
		
		request.setAttribute("addr", addr);
		request.setAttribute("user", user);
		request.getRequestDispatcher("mycenter.jsp").forward(request, response);
		}
	}

	/**
	 * 修改个人资料 ajax
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void submit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		user = new Users();
		String[] hobbys = new String[]{};
		String address = "";
		String hobby = "";
		String career = "";
		int age = 0;
		
		Users u = (Users)request.getSession().getAttribute("userinfo");
		int userId = u.getU_id();
		user.setU_id(userId);
		
//昵称
		String nickName = request.getParameter("nickName");
		if(nickName.length()>20){
			request.setAttribute("nickMsg", "昵称不能超过20个字");
			request.getRequestDispatcher("MyCenterServlet?op=query").forward(request, response);
			return;
		}
		user.setNickName(nickName);

//性别
		String sex = request.getParameter("sex");
		if(sex.length()>2){
			request.setAttribute("centMsg", "性别不能大于两位");
			request.getRequestDispatcher("MyCenterServlet?op=query").forward(request, response);
			return;
		}
		user.setSex(sex);

//年龄
		String sage = request.getParameter("age");
//		判断是否为数字
		if(sage.length()>3){
			request.setAttribute("ageMsg", "请输入正确的年龄");
			request.getRequestDispatcher("MyCenterServlet?op=query").forward(request, response);
			return;
		}
		if(sage!=null){
			age = Integer.parseInt(sage);
		}
		user.setAge(age);

//email
		String email = request.getParameter("email");
		if(email.length()>20){
			request.setAttribute("emailMsg", "邮箱不能超过20位");
			request.getRequestDispatcher("MyCenterServlet?op=query").forward(request, response);
			return;
		}
		user.setEmail(email);
		
//phone
		String pon = request.getParameter("pon");
		if(pon.length()>11){
			request.setAttribute("ponMsg", "请输入正确的号码");
			request.getRequestDispatcher("MyCenterServlet?op=query").forward(request, response);
			return;
		}
		user.setPhone(pon);
		
//地址
		String province = request.getParameter("pro");
		String city = request.getParameter("city");
		if(province!=null&&city!=null){
			address = province + " " + city;
		}
		user.setAddress(address);
		
//职业
		String occu = request.getParameter("occu");
		if(occu!=null&&!"".equals(occu)){
			career = occu;
		}
		user.setCareer(career);
		
//爱好
		if(request.getParameterValues("check")!=null){
			hobbys = request.getParameterValues("check");
			for(int i=0 ; i < hobbys.length; i++){
				hobby = hobby+","+hobbys[i];
			}
			
		}
		user.setHobby(hobby.substring(1));
		
//签名
		String areaa = request.getParameter("areaa");
		if(areaa.length()>50){
			request.setAttribute("areaMsg","不能超过50个字");
			request.getRequestDispatcher("MyCenterServlet?op=query").forward(request, response);
			return;
		}
		user.setIntrodution(areaa);
	
		
		if(userSer.modUser(user)){
			this.query(request, response);
		}
		
		
		
	}

	/**
	 * ajax上传头像
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void head(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 响应回去的是一个json格式的对象。
		response.setContentType("application/json;charset=UTF-8");
		
		
		// 获取要上传的文件
		Part part = request.getPart("file");
		// 获取请求信息
		String name = part.getHeader("content-disposition");

		// 上传路径
		String tempPath = "/headpic";

		// 获取上传文件的目录
		String root = request.getServletContext().getRealPath(tempPath);

		// 获取文件后缀
		String suffix = name
				.substring(name.lastIndexOf("."), name.length() - 1);
		// UUID生成新文件名
		String filePath = "t"+UUID.randomUUID().toString().replace("-", "") + suffix;
//		获取session中的id更改头像路径
		Users user = (Users)request.getSession().getAttribute("userinfo");
		int userId = user.getU_id();
		if(!userSer.modUser(filePath,userId)){
			return;
		}
		
		String filename = root + "\\" + filePath;
		// 上传到服务器
		part.write(filename);
		
//		ajax返回
		Gson gson = new Gson();
		String json = gson.toJson(filePath);
		response.getWriter().println(json);

		//request.getRequestDispatcher("up.jsp").forward(request, response);

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
