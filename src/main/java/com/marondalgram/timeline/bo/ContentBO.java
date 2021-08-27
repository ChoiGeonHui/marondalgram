package com.marondalgram.timeline.bo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marondalgram.commen.FileManagerSurvice;
import com.marondalgram.comment.bo.CommentBO;
import com.marondalgram.comment.model.Comment;
import com.marondalgram.like.bo.LikeBO;
import com.marondalgram.post.model.Post;
import com.marondalgram.timeline.dao.TimelineDAO;
import com.marondalgram.timeline.domain.Content;

@Service
public class ContentBO {
	
	@Autowired
	TimelineBO timelineBO;
	
	@Autowired
	TimelineDAO timelineDAO;
	
	@Autowired
	CommentBO commentBO;
	
	@Autowired
	LikeBO likeBO;
	
	
	public List<Content> AllPostListView(Integer userId){
		
		List<Content> contentList = new ArrayList<>();
		List<Post> postlist = timelineDAO.selectListPostById();
		
		for (Post post : postlist) {
			Content content = new Content();
			content.setPost(post);
			
			List<Comment> commentlist = commentBO.selectCommetList(post.getId());
			content.setCommentlist(commentlist);
			
			boolean postlike = likeBO.fillLikeByPostIdAndUserId(userId, post.getId());
			content.setFillLike(postlike);
			
			content.setLikeCount(likeBO.likeConunt(post.getId()));
			
			contentList.add(content);
			
		}
		return contentList;
		

	};
	
	
	//삭제할 개시물의 좋아요,댓글,파일,내용 전체 삭제
	public void AllDeletePostByPostId(int postId) {
		
		Post post = timelineBO.getPost(postId);
		String imagePath = post.getImagePath();
		
		FileManagerSurvice fileManagerSurvice = new FileManagerSurvice();
		
		try {
			fileManagerSurvice.deleteFile(imagePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		likeBO.deletePostLike(postId);
		commentBO.deletePostComment(postId);
		timelineBO.deletePost(postId);
	}
	
	

}
