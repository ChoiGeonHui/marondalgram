package com.marondalgram.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


public class WebMvcConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		.addResourceHandler("/images/**") // http://localhost/images/1_1620995857660/sun.png �� ���� ���� �����ϰ� �������ش�. 
		.addResourceLocations("file:///D:\\\\images/");
		//���� ���� ������ġ
	}


}
