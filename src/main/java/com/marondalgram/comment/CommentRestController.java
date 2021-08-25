package com.marondalgram.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marondalgram.comment.bo.CommentBO;

@RestController
@RequestMapping("/comment")
public class CommentRestController {
	
	@Autowired
	CommentBO commentBO;
	
	@RequestMapping("/create")
	public Map<String, String> insertComment(
			@RequestParam("postId") int postId,
			@RequestParam("content") String content,
			HttpServletRequest request
			){
		
		HttpSession session = request.getSession();
		int userId =(int) session.getAttribute("userId");
		String userName = (String) session.getAttribute("userName");
		
		
		int row =commentBO.insertComment(postId, userId, userName, content);
		
		Map<String , String> result = new HashMap<>();
		
		if(row >0) {
		result.put("result", "success");
		}else {
			result.put("result", "fail");	
		}
		
		return result;
	}

}
