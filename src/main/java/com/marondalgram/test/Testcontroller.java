package com.marondalgram.test;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.marondalgram.test.bo.TestBO;

@Controller
public class Testcontroller {
	
	
	@Autowired
	TestBO testBO;
	
	@RequestMapping("/test01")
	@ResponseBody
	public String test() {
		return "hello world!";
	}
	
	
	@RequestMapping("/test01_db")
	@ResponseBody
	public Map<String, Object> view(){
		
		Map<String, Object> list=testBO.view() ;
		return list;
	}
	
	
	@RequestMapping("/test01_jsp")
	public String viewjsp() {
		return "test/newtest";
	}
	

}
