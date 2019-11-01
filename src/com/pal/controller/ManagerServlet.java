package com.pal.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pal.dao.BlogsDao;
import com.pal.dao.InformDao;
import com.pal.dao.UsersDao;
import com.pal.entity.Admin;
import com.pal.entity.Blogs;
import com.pal.entity.ChatMessages;
import com.pal.entity.Inform;
import com.pal.entity.Page;
import com.pal.entity.Pictures;
import com.pal.entity.Users;
import com.pal.service.AdminService;
import com.pal.service.BlogsService;
import com.pal.service.ChatMessagesService;
import com.pal.service.CommentsService;
import com.pal.service.InformService;
import com.pal.service.PicturesService;
import com.pal.service.UsersService;

public class ManagerServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	AdminService  adminSer = new AdminService();
	UsersDao userDao=new UsersDao();
	BlogsDao blogDao=new BlogsDao();
	InformDao informDao=new InformDao();
	/**
	 * Constructor of the object.
	 */
	public ManagerServlet() {
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
		String op =request.getParameter("op");
		if(op.equals("login")){
			this.login(request,response);
		}else if (op.equals("queryhot")){
			this.queryhot(request,response);
		}else if (op.equals("forbidblog")){//封禁动态
			this.forbidblog(request,response);
		}else if (op.equals("forbiduser")){//封禁用户
			this.forbiduser(request,response);
		}else if (op.equals("forfreeuser")){//释放用户
			this.forfreeuser(request,response);
		}else if(op.equals("queryuser")){//用户管理
			this.queryuser(request,response);
		}else if(op.equals("queryblog")){//动态管理
			this.queryblog(request,response);
		}else if(op.equals("querypic")){//相册管理
			this.querypic(request,response);
		}else if(op.equals("queryinform")){//举报管理
			this.queryinform(request,response);
		}else if (op.equals("forbidinform")){//处理封禁
			this.forbidinform(request,response);
		}else if (op.equals("forfreeinform")){//处理释放
			this.forfreeinform(request,response);
		}else if (op.equals("delpic")){//已违规删除图片
			this.delpic(request,response);
		}else if(op.equals("querychart")){//统计
			this.querychart(request,response);
		}else if(op.equals("warning")){
			this.warning(request,response);
		}
	}

	

	private void warning(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String u_id=request.getParameter("u_id");
		int UId=0;
		if(u_id!=null&&!"".equals(u_id)){
			UId=Integer.parseInt(u_id);
		}
		ChatMessagesService cmService=new ChatMessagesService();
		ChatMessages cm=new ChatMessages();
		
		java.util.Date d = new java.util.Date();
		java.sql.Date date = new java.sql.Date(d.getTime());
		
		cm.setFromuserId(16);
		cm.setTouserId(UId);
		cm.setContent("您已被警告，请注意网络言行");
		cm.setReadstate("0");
		cm.setSendTime(date);
				
		cmService.addChatMessages(cm);
		request.setAttribute("warnMsg", "警告成功");
		/*request.getRequestDispatcher("ManagerServlet?op=queryhot").forward(request, response);*/
		request.getRequestDispatcher("ManagerServlet?op=queryinform").forward(request, response);
	}

	private void delpic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String pictureId=request.getParameter("pictureid");
		
		PicturesService pictureService=new PicturesService();
		int picId=0;
		if(pictureId !=null &&!"".equals(pictureId)){
			picId=Integer.parseInt(pictureId);
		}
		if(pictureService.delPictures(picId)){
			//System.out.println("违规图片已经成功删除");
			request.setAttribute("delMsg", "违规图片已经成功删除");
		}else{
			//System.out.println("违规图片删除失败");
		}
		
		this.querypic(request, response);
	}

	private void forfreeinform(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String stri_id=request.getParameter("i_id");
		
		UsersService usersService=new UsersService();
		InformService informService=new InformService();
		int i_id=0;
		if(stri_id !=null &&!"".equals(stri_id)){
			i_id=Integer.parseInt(stri_id);
		}
		if(informService.freeInform(i_id)){
			//System.out.println("释放成功");
			request.setAttribute("forfreeMsg", "释放成功");
		}else{
			//System.out.println("释放成功");
		}
		
		this.queryinform(request, response);
	}

	private void forbidinform(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String stri_id=request.getParameter("i_id");
		
		InformService informService=new InformService();
		//InformDao informDao=new InformDao();
		//UsersService usersService=new UsersService();
		int i_id=0;
		if(stri_id !=null &&!"".equals(stri_id)){
			i_id=Integer.parseInt(stri_id);
		}
		
		/*if(informService.isDealInform(i_id)){
			System.out.println("处理封禁成功");
			request.setAttribute("msg", "处理封禁成功");
		}else{
			System.out.println("处理封禁失败");
		}*/
		if(informService.isDealBanInform(i_id)){
			//System.out.println("处理封禁成功");
			request.setAttribute("forbidMsg", "处理封禁成功");
		}else{
			//System.out.println("处理封禁失败");
		}
		
		this.queryinform(request, response);
	
	}

	private void forfreeuser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String stru_id=request.getParameter("u_id");
		
		UsersService usersService=new UsersService();
		int u_id=0;
		if(stru_id !=null &&!"".equals(stru_id)){
			u_id=Integer.parseInt(stru_id);
		}
		if(usersService.freeUser(u_id)){
			//System.out.println("释放成功");
			request.setAttribute("forfreeMsg", "释放成功");
		}else{
			//System.out.println("释放成功");
		}
		
		this.queryuser(request, response);
	}

	private void forbiduser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String stru_id=request.getParameter("u_id");
		
		UsersService usersService=new UsersService();
		int u_id=0;
		if(stru_id !=null &&!"".equals(stru_id)){
			u_id=Integer.parseInt(stru_id);
		}
		ChatMessagesService cmService=new ChatMessagesService();
		ChatMessages cm=new ChatMessages();
		
		java.util.Date d = new java.util.Date();
		java.sql.Date date = new java.sql.Date(d.getTime());
		cm.setFromuserId(16);
		cm.setTouserId(u_id);
		cm.setContent("您已被封禁，将无法评论");
		cm.setReadstate("0");
		cm.setSendTime(date);
		
		cmService.addChatMessages(cm);
		if(usersService.isBanUSer(u_id)){
			//System.out.println("封禁成功");
			request.setAttribute("forbidMsg", "封禁成功");
		}else{
			//System.out.println("封禁失败");
		}
		
		this.queryuser(request, response);
	}

	private void querychart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		
		
		
		request.getRequestDispatcher("charts.jsp").forward(request, response);

	}

	private void queryinform(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		InformService informService=new InformService();
		
		int currPage=1;
		int pageSize=5;
		String strCurrPage=request.getParameter("currPage");
		if(strCurrPage!=null&&!"".equals(strCurrPage)){
			currPage=Integer.parseInt(strCurrPage);
		}
		String strPageSize=request.getParameter("pageSize");
		if(strPageSize!=null&&!"".equals(strPageSize)){
			pageSize=Integer.parseInt(strPageSize);
		}
		
		Page<Inform> informPage=informService.getPage(currPage, pageSize);
		request.setAttribute("informPage", informPage);
		request.setAttribute("informList", informPage.getList());
		
		int currPageInform=1;
		int pageSizeInform=5;
		String strCurrPageInform=request.getParameter("currPageInform");
		if(strCurrPageInform!=null&&!"".equals(strCurrPageInform)){
			currPageInform=Integer.parseInt(strCurrPageInform);
		}
		String strPageSizeInform=request.getParameter("pageSizeInform");
		if(strPageSizeInform!=null&&!"".equals(strPageSizeInform)){
			pageSizeInform=Integer.parseInt(strPageSizeInform);
		}
		//已处理的被举报用户
		Page<Inform> informBanPage=informService.getBanPage(currPageInform, pageSizeInform);
		request.setAttribute("informBanPage", informBanPage);
		request.setAttribute("informBanList", informBanPage.getList());
		
		
		
		request.getRequestDispatcher("reportmanage.jsp").forward(request, response);

	}

	private void querypic(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		PicturesService pictureService=new PicturesService();
		int currPagePic=1;
		int pageSizePic=5;
		String strCurrPagePic=request.getParameter("currPagePic");
		if(strCurrPagePic!=null&&!"".equals(strCurrPagePic)){
			currPagePic=Integer.parseInt(strCurrPagePic);
		}
		String strPageSizePic=request.getParameter("pageSizePic");
		if(strPageSizePic!=null&&!"".equals(strPageSizePic)){
			pageSizePic=Integer.parseInt(strPageSizePic);
		}
		Page<Pictures> picPage=pictureService.getPage(currPagePic, pageSizePic);
		request.setAttribute("picPage", picPage);
		request.setAttribute("picList", picPage.getList());
		request.getRequestDispatcher("picmanage.jsp").forward(request, response);
	}

	private void queryblog(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		BlogsService blogService = new BlogsService();
		int currPageBlog=1;
		int pageSizeBlog=5;
		String strCurrPageBlog=request.getParameter("currPageBlog");
		if(strCurrPageBlog!=null&&!"".equals(strCurrPageBlog)){
			currPageBlog=Integer.parseInt(strCurrPageBlog);
		}
		String strPageSizeBlog=request.getParameter("pageSizeBlog");
		if(strPageSizeBlog!=null&&!"".equals(strPageSizeBlog)){
			pageSizeBlog=Integer.parseInt(strPageSizeBlog);
		}
		//Page<Blogs> blogHotPage=blogService.getHotPage(currPageBlog, pageSizeBlog);
		Page<Blogs> blogPage=blogService.getPage(currPageBlog, pageSizeBlog);
		
		request.setAttribute("blogPage", blogPage);
		
		request.setAttribute("blogList", blogPage.getList());
		//request.setAttribute("blogHotList", blogHotPage.getList());	
		request.getRequestDispatcher("blogmanage.jsp").forward(request, response);
	}
	
	
	
	private void queryuser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		UsersService userService=new UsersService();
		int currPage=1;
		int pageSize=5;
		String strCurrPage=request.getParameter("currPage");
		if(strCurrPage!=null&&!"".equals(strCurrPage)){
			currPage=Integer.parseInt(strCurrPage);
		}
		String strPageSize=request.getParameter("pageSize");
		if(strPageSize!=null&&!"".equals(strPageSize)){
			pageSize=Integer.parseInt(strPageSize);
		}
		
		Page<Users> userPage=userService.getPage(currPage, pageSize);
		request.setAttribute("userPage", userPage);
		request.setAttribute("userList", userPage.getList());
		
		/*查询封禁用户信息*/
		int currPageUser=1;
		int pageSizeUser=5;
		String strCurrPageUser=request.getParameter("currPageUser");
		if(strCurrPageUser!=null&&!"".equals(strCurrPageUser)){
			currPageUser=Integer.parseInt(strCurrPageUser);
		}
		String strPageSizeUser=request.getParameter("pageSizeUser");
		if(strPageSizeUser!=null&&!"".equals(strPageSizeUser)){
			pageSizeUser=Integer.parseInt(strPageSizeUser);
		}
		Page<Users> userBanPage=userService.getPageByIsBan(currPageUser, pageSizeUser);
		request.setAttribute("userBanPage", userBanPage);
		request.setAttribute("userBanList", userBanPage.getList());	
		//System.out.println("userBanPage="+userBanPage);
		
		
		
		request.getRequestDispatcher("usermanage.jsp").forward(request, response);
	}

	private void forbidblog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String strB_id=request.getParameter("b_id");
		
		BlogsService blogService = new BlogsService();
		//CommentsService commentService=new CommentsService();
		int b_id=0;
		if(strB_id !=null &&!"".equals(strB_id)){
			b_id=Integer.parseInt(strB_id);
		}
		if(blogService.delBlogs(b_id)){
			System.out.println("删除成功");
			request.setAttribute("delmsg", "删除成功");
		}else{
			System.out.println("删除失败");
		}
		this.queryblog(request, response);
	}

	private void queryhot(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BlogsService blogService = new BlogsService();
		UsersService userService=new UsersService();
		PicturesService pictureService=new PicturesService();
		InformService informService=new InformService();
		

		
		ArrayList<Inform> informList=informService.getAllInform();
		//ArrayList<Inform> informList=informService.getHotInform();
		request.setAttribute("informList", informList);
		
		int currPage=1;
		int pageSize=5;
		String strCurrPage=request.getParameter("currPage");
		if(strCurrPage!=null&&!"".equals(strCurrPage)){
			currPage=Integer.parseInt(strCurrPage);
		}
		String strPageSize=request.getParameter("pageSize");
		if(strPageSize!=null&&!"".equals(strPageSize)){
			pageSize=Integer.parseInt(strPageSize);
		}
		/*Page<Blogs> bolgsPage=blogService.getPage(currPage, pageSize);
		request.setAttribute("bolgsPage", bolgsPage);*/
		
		
		int userNum=userDao.getNumUser();//用户注册数量
		request.setAttribute("userNum", userNum);
		int userNum1=userDao.getNumUsers1();//当天注册新用户的数量
		request.setAttribute("userNum1", userNum1);
		
		int blogHot=blogDao.getHotBlog();//热门动态数量
		request.setAttribute("blogHot", blogHot);
		int blogNum=blogDao.getNumBlog();//新动态数量
		request.setAttribute("blogNum", blogNum);
		
		int informNum=informDao.getNewInform();//被举报的数量
		request.setAttribute("informNum", informNum);
		
		/*查询所有用户信息*/
		Page<Users> userPage=userService.getPage(currPage, pageSize);
		request.setAttribute("userPage", userPage);
		//ArrayList<Users> userList=userService.getAllUsers();
		request.setAttribute("userList", userPage.getList());	
		
		
		
		
		int currPagePic=1;
		int pageSizePic=5;
		String strCurrPagePic=request.getParameter("currPagePic");
		if(strCurrPagePic!=null&&!"".equals(strCurrPagePic)){
			currPagePic=Integer.parseInt(strCurrPagePic);
		}
		String strPageSizePic=request.getParameter("pageSizePic");
		if(strPageSizePic!=null&&!"".equals(strPageSizePic)){
			pageSizePic=Integer.parseInt(strPageSizePic);
		}
		Page<Pictures> picPage=pictureService.getPage(currPagePic, pageSizePic);
		request.setAttribute("picPage", picPage);
		//ArrayList<Pictures> picList=pictureService.getAllPictures();
		request.setAttribute("picList", picPage.getList());
		
		
		int currPageBlog=1;
		int pageSizeBlog=5;
		String strCurrPageBlog=request.getParameter("currPageBlog");
		if(strCurrPageBlog!=null&&!"".equals(strCurrPageBlog)){
			currPageBlog=Integer.parseInt(strCurrPageBlog);
		}
		String strPageSizeBlog=request.getParameter("pageSizeBlog");
		if(strPageSizeBlog!=null&&!"".equals(strPageSizeBlog)){
			pageSizeBlog=Integer.parseInt(strPageSizeBlog);
		}
		Page<Blogs> blogHotPage=blogService.getHotPage(currPageBlog, pageSizeBlog);
		Page<Blogs> blogPage=blogService.getPage(currPageBlog, pageSizeBlog);
		request.setAttribute("blogHotPage", blogHotPage);
		request.setAttribute("blogPage", blogPage);
		//ArrayList<Blogs> blogList = blogService.getHotBlogs();
		//ArrayList<Blogs> blogList = blogService.getAllBlogs();
		//request.setAttribute("blogList", blogList);
		request.setAttribute("blogList", blogPage.getList());
		request.setAttribute("blogHotList", blogHotPage.getList());	
		
		
		
		int currPageInform=1;
		int pageSizeInform=5;
		String strCurrPageInform=request.getParameter("currPageInform");
		if(strCurrPageInform!=null&&!"".equals(strCurrPageInform)){
			currPageInform=Integer.parseInt(strCurrPageInform);
		}
		String strPageSizeInform=request.getParameter("pageSizeInform");
		if(strPageSizeInform!=null&&!"".equals(strPageSizeInform)){
			pageSizeInform=Integer.parseInt(strPageSizeInform);
		}
		//被举报用户
		Page<Inform> informPage=informService.getPage(currPageInform, pageSizeInform);
		request.setAttribute("informPage", informPage);
		request.setAttribute("informList", informPage.getList());
		
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String name =request.getParameter("mname");
		String pwd = request.getParameter("mpassword");
		HttpSession session = request.getSession();
		Admin admin = adminSer.getAdmin(name);
		if(admin==null){
			session.setAttribute("loginMsg", "用户名错误");
			response.sendRedirect("login.jsp");
		}else{
			if(pwd.equals(admin.getA_pwd())){
				session.setAttribute("admin", admin);
				response.sendRedirect("ManagerServlet?op=queryhot");
			}else{
				session.setAttribute("loginMsg", "密码错误");
				response.sendRedirect("login.jsp");
			}
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
