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
/**
 * 设置编码
 * @author Administrator
 *
 */
public class EncodeFilter implements Filter {

	private String encode;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		//获取web.xml的初始化配置信息
		 encode = filterConfig.getServletContext().getInitParameter("encode");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub

		String[] urls={".css",".js",".png",".gif",".jpg",".woff"};
		//获取用户请求的url地址
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		String uri = req.getRequestURI();
		
		//System.out.print("uri="+uri);
		boolean flag = false;
		for(String url:urls){
			if(uri.endsWith(url)){
				flag = true;
				break;
			}
		}
		
		if(flag){//放行的资源
			//System.out.println("放行");
			chain.doFilter(request, response);
			
		}else{
			//System.out.println("编码处理");
			req.setCharacterEncoding(encode);
			resp.setCharacterEncoding(encode);
			
			chain.doFilter(request, response);
			
		}
		

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
