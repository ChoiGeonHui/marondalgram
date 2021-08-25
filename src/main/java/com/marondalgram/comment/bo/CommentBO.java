package com.marondalgram.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marondalgram.comment.dao.CommentDAO;
import com.marondalgram.comment.model.Comment;

@Service
public class CommentBO {
	
	@Autowired
	CommentDAO commentDAO;
	
	public List<Comment> selectCommetList(int postId) {
		return commentDAO.selectCommentListByPostId(postId);
	}

	
	public int insertComment(int postId,
			int userId,String userName,String content) {
		return commentDAO.insertComment(postId, userId, userName, content);
	}
}
