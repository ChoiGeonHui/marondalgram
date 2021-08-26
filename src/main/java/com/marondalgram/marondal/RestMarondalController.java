package com.marondalgram.marondal;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marondalgram.commen.EncryptUtils;
import com.marondalgram.marondal.bo.MarondalBO;
import com.marondalgram.marondal.model.User;

@RestController
@RequestMapping("/log")
public class RestMarondalController {
	
	@Autowired
	MarondalBO marondalBO;
	
	/**
	 * 아이디중복확인 api
	 * @param loginId
	 * @return
	 */
	@RequestMapping("is_dulicated_id")
	public Map<String, Boolean> chkId(
			@RequestParam("loginId")String loginId){
		
		Map<String, Boolean> result = new HashMap<>();
		User user=marondalBO.selectUserById(loginId);
		
		if(user == null) {
			result.put("result", false);
		}else {
			result.put("result", true);			
		}
		
		return result;
	}
	
	/**
	 * 로그인 api
	 * @param loginId
	 * @param password
	 * @param request
	 * @return
	 */
	@PostMapping("/sign_in")
	public Map<String, String> login(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpServletRequest request){
		
		String enPassword =EncryptUtils.md5(password);
		
		User user = marondalBO.getUserById(loginId, enPassword);
		Map<String, String> result =new HashMap<>();	
		if(user !=null) {
			result.put("result", "success");
			
			HttpSession session = request.getSession();
			session.setAttribute("userName",user.getName());
			session.setAttribute("userloginId",user.getLoginId());
			session.setAttribute("userId",user.getId());
			
		}else {
			result.put("result", "fail");
		}
			
		return result;
	}
	
	/**
	 * 회원가입 api
	 * @param loginId
	 * @param password
	 * @param name
	 * @param email
	 * @return
	 */
	@PostMapping("/signup_forajax")
	public Map<String, String> signupforajax(
			@RequestParam("loginId")String loginId,
			@RequestParam("password")String password,
			@RequestParam("name")String name,
			@RequestParam("email")String email
			){
		
		String encrypassword= EncryptUtils.md5(password);
		marondalBO.insertUser(loginId, encrypassword, name, email);
		
		Map<String, String> result=new HashMap<>();
		result.put("result", "success");
		return result;
	}
	
	
	

}
