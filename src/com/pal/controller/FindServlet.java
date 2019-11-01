package com.pal.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pal.entity.Page;
import com.pal.entity.Users;
import com.pal.service.UsersService;

public class FindServlet extends HttpServlet {
	Users user = new Users();
	Page<Users> page = new Page<Users>();
	UsersService userSer = new UsersService();

	/**
	 * Constructor of the object.
	 */
	public FindServlet() {
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
		// 响应回去的是一个json格式的对象。
		response.setContentType("application/json;charset=UTF-8");
		int currPage = 1;
		int pageSize = 12;
		int minage = 0;
		int maxage = 0;
		String address = "";
		String[] age = new String[] {};
		
		
		String curr = request.getParameter("currPage");
		if(curr!=null&&!"".equals(curr)){
			currPage = Integer.parseInt(curr);
		}
		
		String nickName = request.getParameter("nickName");
		String sex = request.getParameter("sex");
		String pro = request.getParameter("province");
		String city = request.getParameter("city");
		String ages = request.getParameter("age");
		// 特 昵称模糊搜索
		if (nickName != null && !"".equals(nickName)) {
			page = new Page<Users>();
			page = userSer.getPage(nickName, currPage, pageSize);
			Gson gson = new Gson();
			String json = gson.toJson(page);
			response.getWriter().println(json);
			
			return;
		} else {
			// 第一种 性别
			if (!"不限".equals(sex) && "不限".equals(pro) && "不限".equals(city)
					&& "不限".equals(ages)) {
				page = new Page<Users>();
				page = userSer.getPageBySex(sex, currPage, pageSize);
				Gson gson = new Gson();
				String json = gson.toJson(page);
				response.getWriter().println(json);
				
				return;
			}
			// 第二种 省市
			if (!"不限".equals(pro) && "不限".equals(city) && "不限".equals(sex)
					&& "不限".equals(ages)) {
				address = "%" + pro + "%";
				page = new Page<Users>();
				page = userSer.getPageByAdd(address, currPage, pageSize);
				Gson gson = new Gson();
				String json = gson.toJson(page);
				response.getWriter().println(json);
				//System.out.println(2);
				return;
			}
			if (!"不限".equals(pro) && !"不限".equals(city) && "不限".equals(sex)
					&& "不限".equals(ages)) {
				address = "%" + pro + "%" + city;
				page = new Page<Users>();
				page = userSer.getPageByAdd(address, currPage, pageSize);
				Gson gson = new Gson();
				String json = gson.toJson(page);
				response.getWriter().println(json);
				//System.out.println(3);
				return;
			}
			// 第三种 年龄
			if (!"不限".equals(ages) && "不限".equals(sex) && "不限".equals(pro)
					&& "不限".equals(city)) {
				age = ages.split("-");
				minage = Integer.parseInt(age[0]);
				maxage = Integer.parseInt(age[1]);
				page = new Page<Users>();
				page = userSer.getPageByAge(minage, maxage, currPage, pageSize);
				Gson gson = new Gson();
				String json = gson.toJson(page);
				response.getWriter().println(json);
				//System.out.println(4);
				return;
			}
			// 第四种 性别,省市
			if (!"不限".equals(sex) && !"不限".equals(pro) && "不限".equals(city)
					&& "不限".equals(ages)) {
				address = "%"+pro+"%";
				page = new Page<Users>();
				page = userSer.getPageBySexAdd(sex, address, currPage, pageSize);
				Gson gson = new Gson();
				String json = gson.toJson(page);
				response.getWriter().println(json);
				//System.out.println(5);
				return;
			}
			if (!"不限".equals(sex) && !"不限".equals(pro) && !"不限".equals(city)
					&& "不限".equals(ages)) {
				address = "%" + pro + "%" + city;
				page = new Page<Users>();
				page = userSer.getPageBySexAdd(sex, address, currPage, pageSize);
				Gson gson = new Gson();
				String json = gson.toJson(page);
				response.getWriter().println(json);
				//System.out.println(6);
				return;
			}
			// 第五种 性别,年龄
			if(!"不限".equals(sex) && "不限".equals(pro) && "不限".equals(city)
					&& !"不限".equals(ages)){
				age = ages.split("-");
				minage = Integer.parseInt(age[0]);
				maxage = Integer.parseInt(age[1]);
				page = new Page<Users>();
				page = userSer.getPageBySexAge(sex, minage, maxage, currPage, pageSize);
				Gson gson = new Gson();
				String json = gson.toJson(page);
				response.getWriter().println(json);
				//System.out.println(7);
				return;
			}
			// 第六种 性别,省市,年龄
			if(!"不限".equals(sex) && !"不限".equals(pro) && "不限".equals(city)
					&& !"不限".equals(ages)){
				address = "%"+pro+"%";
				age = ages.split("-");
				minage = Integer.parseInt(age[0]);
				maxage = Integer.parseInt(age[1]);
				page = new Page<Users>();
				page = userSer.getPageBySexAddAge(sex, address, minage, maxage, currPage, pageSize);
				Gson gson = new Gson();
				String json = gson.toJson(page);
				response.getWriter().println(json);
				//System.out.println(8);
				return;
			}
			if(!"不限".equals(sex) && !"不限".equals(pro) && !"不限".equals(city)
					&& !"不限".equals(ages)){
				address = "%" + pro + "%" + city;
				age = ages.split("-");
				minage = Integer.parseInt(age[0]);
				maxage = Integer.parseInt(age[1]);
				page = new Page<Users>();
				page = userSer.getPageBySexAddAge(sex, address, minage, maxage, currPage, pageSize);
				Gson gson = new Gson();
				String json = gson.toJson(page);
				response.getWriter().println(json);
				//System.out.println(9);
				return;
			}
			// 第七种 省市,年龄
			if(!"不限".equals(pro) && "不限".equals(city) && "不限".equals(sex)
					&& !"不限".equals(ages)){
				address = "%"+pro+"%";
				age = ages.split("-");
				minage = Integer.parseInt(age[0]);
				maxage = Integer.parseInt(age[1]);
				page = new Page<Users>();
				page = userSer.getPageByAddAge(address, minage, maxage, currPage, pageSize);
				Gson gson = new Gson();
				String json = gson.toJson(page);
				response.getWriter().println(json);
				//System.out.println(10);
				return;
			}
			if(!"不限".equals(pro) && !"不限".equals(city) && "不限".equals(sex)
					&& !"不限".equals(ages)){
				address = "%" + pro + "%" + city;
				age = ages.split("-");
				minage = Integer.parseInt(age[0]);
				maxage = Integer.parseInt(age[1]);
				page = new Page<Users>();
				page = userSer.getPageByAddAge(address, minage, maxage, currPage, pageSize);
				Gson gson = new Gson();
				String json = gson.toJson(page);
				response.getWriter().println(json);
				//System.out.println(11);
				return;
			}
//			所有为空
			page = new Page<Users>();
			page = userSer.getPage(currPage, pageSize);
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
