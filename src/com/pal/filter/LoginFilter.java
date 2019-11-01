package com.pal.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.pal.entity.Users;

/**
 * 用户登录验证
 * 
 * @author Administrator
 * 
 */
public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
//		设置通行过滤后缀
		String[] urls={".css",".js",".png",".gif",".jpg",".woff","login.jsp","index.jsp","PlazaServlet?op=query","UserServlet","register.jsp"};
		
//	获取uri
		String uri = req.getRequestURI();
		
//		判断是否进行过滤
		boolean flag = false;
		for(String url:urls){
			if(uri.endsWith(url)){
				flag = true;
				break;
			}
		}
		
		Users user = (Users) req.getSession().getAttribute("userinfo");
		if (!flag && user == null ) {

			// 跳转到登录页面
			req.getSession().setAttribute("namemsg", "请先登录。");
			resp.sendRedirect("login.jsp");
			return;

		}else{
			chain.doFilter(request, response);
		}
		

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
