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
	
	/*
	 * @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
	 * registry.addResourceHandler("/images/**")
	 * .addResourceLocations("file:///"+FileManagerSurvice.FILE_UPLOAD_PATH); }
	 */
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**") //http://localhost/images/.. 와 같이 접근 가능하게 맵핑한다.
		.addResourceLocations("file:///"+FileManagerSurvice.FILE_UPLOAD_PATH);
		//실제 파일 저장위치
	}


}
