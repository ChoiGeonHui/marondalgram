package com.marondalgram.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class PermissionInterceptor  implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String userloginId =(String) session.getAttribute("userloginId");
		
		String uri =request.getRequestURI();
		System.out.println("############## : "+ uri);
		if(userloginId == null && (uri.startsWith("/post")||uri.startsWith("/timeline"))) {
			response.sendRedirect("/user/sign_in_view");
			return false;
		}
		
		if(userloginId != null && uri.startsWith("/marondalgram")) {
			response.sendRedirect("/timeline");
			return false;
		}
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		System.out.println("#### posthandle: "+uri);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		System.out.println("#### afterComletion: "+uri);
		
	}
	


}
