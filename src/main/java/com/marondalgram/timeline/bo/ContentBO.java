package com.marondalgram.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marondalgram.comment.bo.CommentBO;
import com.marondalgram.comment.model.Comment;
import com.marondalgram.like.bo.LikeBO;
import com.marondalgram.like.model.Like;
import com.marondalgram.post.model.Post;
import com.marondalgram.timeline.dao.TimelineDAO;
import com.marondalgram.timeline.domain.Content;

@Service
public class ContentBO {
	
	@Autowired
	TimelineDAO timelineDAO;
	
	@Autowired
	CommentBO commentBO;
	
	@Autowired
	LikeBO likeBO;
	
	
	public List<Content> AllPostListView(){
		
		List<Content> contentList = new ArrayList<>();
		List<Post> postlist = timelineDAO.selectListPostById();
		
		for (Post post : postlist) {
			Content content = new Content();
			content.setPost(post);
			
			List<Comment> commentlist = commentBO.selectCommetList(post.getId());
			content.setCommentlist(commentlist);
			
			List<Like> likelist = likeBO.selectLikeByPostId(post.getId());
			content.setLikelist(likelist);
			
			content.setLikeCount(likeBO.likeConunt(post.getId()));
			
			contentList.add(content);
			
		}
		return contentList;
		

	};

}
