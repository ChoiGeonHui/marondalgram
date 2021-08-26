package com.marondalgram.post;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.marondalgram.comment.bo.CommentBO;
import com.marondalgram.like.bo.LikeBO;
import com.marondalgram.timeline.bo.TimelineBO;


@RestController
@RequestMapping("/post")
public class PostRestController {
	
	@Autowired
	CommentBO commentBO;
	
	@Autowired
	LikeBO likeBO;
	
	@Autowired
	TimelineBO timelineBO;
	
	@RequestMapping("/create")
	public Map<String, String> createPost(
			@RequestParam("content") String content,
			@RequestParam(value =  "file", required = false)
			MultipartFile file,
			HttpServletRequest request
			){
		Map<String, String> result = new HashMap<>();
		HttpSession session = request.getSession();
		
		int userId = (int) session.getAttribute("userId");
		String userName =(String) session.getAttribute("userName");
	
		if(userName ==null) {
			System.out.print(userName+"###########"+ userId);
			result.put("result", "fail");
			return result;
		}
		
		timelineBO.createPost(userId, userName, content, file);
		result.put("result", "success");
		
		return result;
	}
	
	
	@RequestMapping("/delete")
	public Map<String, String> deletePost(
			@RequestParam("postId") int postId,
			@RequestParam("postUserName") String postUserName,
			HttpServletRequest request
			){
		
		Integer userId = null;
		HttpSession session = request.getSession();
		userId =(Integer) session.getAttribute("userId");
		String userName = (String) session.getAttribute("userName");
		
		//System.out.println("###########postUserName = "+ postUserName);
		
		Map<String, String> result =new HashMap<String, String>();
		
		if(userId ==null) {
			result.put("result", "NotUserId");
			return result;
		}else if(!userName.equals(postUserName)) {
			//다른 계정에서 삭제하려는 경우
			result.put("result", "UserIdDifferent");
			return result;
		}
		int row = commentBO.deletePostComment(postId);
		int row2 = likeBO.deletePostLike(postId);
		int row3 = timelineBO.deletePost(postId);
		
		result.put("result", "success");
		
		
		return result;
	}
	
	
	

}
