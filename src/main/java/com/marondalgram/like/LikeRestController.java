package com.marondalgram.like;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marondalgram.like.bo.LikeBO;

@RestController
@RequestMapping("/like")
public class LikeRestController {
	
	@Autowired
	LikeBO likeBO;
	
	/**
	 * 좋아요 하기
	 * @param postId
	 * @param request
	 * @return
	 */
	@RequestMapping("/like")
	public Map<String, String> insertlike(
			@RequestParam("postId") int postId,
			HttpServletRequest request
			){
		HttpSession session= request.getSession();
		Integer userId = null;
		
		userId=(Integer)session.getAttribute("userId");
		Map<String, String> result = new HashMap<String, String>();	
		
		//로그인이 안된경우
		if(userId == null) {
			result.put("result", "userNo");
			return result;
		}
		
		int row = likeBO.insertLike(userId, postId);
		
		if(row>0) {
			result.put("result", "success");
		}else {
			result.put("result", "fail");			
		}

		return result;
	}
	
	/**
	 * 좋아요 취소하기
	 * @param postId
	 * @param request
	 * @return
	 */
	@RequestMapping("/likeCancel")
	public Map<String, String> deletelike(
			@RequestParam("postId") int postId,
			HttpServletRequest request
			){
		HttpSession session= request.getSession();
		Integer userId = null;
		
		userId=(Integer)session.getAttribute("userId");
		Map<String, String> result = new HashMap<String, String>();	
		
		//로그인이 안된경우
		if(userId == null) {
			result.put("result", "userNo");
			return result;
		}
		
		int row = likeBO.deleteLike(userId, postId);
		
		if(row>0) {
			result.put("result", "success");
		}else {
			result.put("result", "fail");			
		}
		
		return result;
	}

}
