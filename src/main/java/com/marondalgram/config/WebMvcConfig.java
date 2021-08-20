package com.marondalgram.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.marondalgram.commen.FileManagerSurvice;
import com.marondalgram.interceptor.PermissionInterceptor;

public class WebMvcConfig implements WebMvcConfigurer{
	
	@Autowired
	PermissionInterceptor permissionInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(permissionInterceptor)
		.addPathPatterns("/**")
		.excludePathPatterns("/user/sign_out","/static/**","/error");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**")
		.addResourceLocations("file:///"+FileManagerSurvice.FILE_UPLOAD_PATH);
	}

}
