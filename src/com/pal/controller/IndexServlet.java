package com.pal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pal.entity.ChatMessages;
import com.pal.entity.Friend;
import com.pal.entity.FriendRequest;
import com.pal.entity.Users;
import com.pal.service.ChatMessagesService;
import com.pal.service.FriendRequestService;
import com.pal.service.FriendService;
import com.pal.service.UsersService;

public class IndexServlet extends HttpServlet {
	UsersService userSer = new UsersService();
	FriendService fSer = new FriendService();
	FriendRequestService frSer = new FriendRequestService();
	ChatMessagesService chatSer = new ChatMessagesService();
	/**
	 * Constructor of the object.
	 */
	public IndexServlet() {
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
		this.doPost(request, response);
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
		String op = request.getParameter("op");
		if("query".equals(op)){
			this.query(request, response);
		}else if("re".equals(op)){
			this.re(request, response);
		}else if("lgonreR".equals(op)){
			this.lgonreR(request, response);
		}else if("lgonreM".equals(op)){
			this.lgonreM(request, response);
		}
	}
	
	/**
	 * 忽略全部请求
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void lgonreR(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = true;
		Users myinfo = (Users)request.getSession().getAttribute("userinfo");
		ArrayList<FriendRequest> fr = new ArrayList<FriendRequest>();
		fr = frSer.getAllFriendRequest(myinfo.getU_id());
		for(FriendRequest frs:fr){
			if(frSer.modFriendRequest(frs.getFr_id())){
			}
			else{flag = false;}
		}
		if(flag){
			response.getWriter().println();
		}
		
	}
	
	/**
	 * 忽略全部消息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void lgonreM(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = true;
		Users myinfo = (Users)request.getSession().getAttribute("userinfo");
		ArrayList<ChatMessages> cm = new ArrayList<ChatMessages>();
		cm = chatSer.getAllChatMessagesE(myinfo.getU_id());
		
		for(ChatMessages chat:cm){
			if(chatSer.modChatMessages(chat.getCmId())){}
			else{flag = false;}
		}
		if(flag){
			response.getWriter().println();
		}
		
	}
	
	/**
	 * 好友请求处理
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void re(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Friend f = new Friend();
		boolean flag = false;
		int fr_id = 0;
		int u_id = 0;
		String datas = request.getParameter("datas");
		String fr_Id = request.getParameter("fr_id");
		String u_Id = request.getParameter("u_id");
		if(fr_Id!=null && !"".equals(fr_Id)){
			fr_id = Integer.parseInt(fr_Id);
		}
		if(u_Id!=null && !"".equals(u_Id)){
			u_id = Integer.parseInt(u_Id);
		}
		
		Users u = new Users();
		u = (Users)request.getSession().getAttribute("userinfo");
		
		
		f.setFromuserId(u.getU_id());
		f.setTouserId(u_id);
		
		if(datas.equals("1")){
//			接受
			if(frSer.modFriendRequest(fr_id)){
				if(fSer.addFriend(f)){
					flag = true;
				}
			}
			
		}else if(datas.equals("2")){
//			拒绝
			if(frSer.modFriendRequest(fr_id)){
				flag = true;
			}
		}
		if(flag){
			response.getWriter().println();
		}
		
	}
	
	
	/**
	 * 主页推荐人
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 响应回去的是一个json格式的对象。
		response.setContentType("application/json;charset=UTF-8");
		ArrayList<FriendRequest> fr = new ArrayList<FriendRequest>();
		ArrayList<ChatMessages> chat = new ArrayList<ChatMessages>();
		List infoList = new ArrayList();
		
		ArrayList<Users> list = userSer.getIndexPeople();
		ArrayList<Users> userList = new ArrayList<Users>();
//		获取10个随机数 显示首页
		int size = list.size();
		if(size>10){
			Set<Integer> set = new HashSet<Integer>();
			Random rand = new Random();
			while(set.size()<10){
				int r = rand.nextInt(list.size());
				set.add(r);
			}
			Iterator<Integer> it = set.iterator();
			while(it.hasNext()){
				userList.add(list.get(it.next()));
			}
		}else{
			userList = list;
		}
		Users u = new Users();
		u = (Users)request.getSession().getAttribute("userinfo");
		if(u==null){
//		ajax
			Gson gson = new Gson();
			String json = gson.toJson(userList);
			response.getWriter().println(json);
		}else{
			int id= u.getU_id();
			fr = frSer.getAllFriendRequest(id);
			chat = chatSer.getAllChatMessagesE(id);
			int frNum = frSer.getTotal(id);
			int chNum = chatSer.getTotalE(id);
			
			infoList.add(userList);
			infoList.add(fr);
			infoList.add(frNum);
			infoList.add(chat);
			infoList.add(chNum);
			
			
			Gson gson = new Gson();
			String json = gson.toJson(infoList);
			response.getWriter().println(json);
		}
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
