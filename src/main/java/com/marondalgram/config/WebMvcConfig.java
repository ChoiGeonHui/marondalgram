package com.marondalgram.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.marondalgram.commen.FileManagerSurvice;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		.addResourceHandler("/images/**") // http://localhost/images/1_1620995857660/sun.png �� ���� ���� �����ϰ� �������ش�. 
		.addResourceLocations("file:///"+FileManagerSurvice.FILE_UPLOAD_PATH);
		//���� ���� ������ġ
	}


}
