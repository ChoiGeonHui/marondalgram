package com.marondalgram.timeline.domain;

import java.util.List;

import com.marondalgram.comment.model.Comment;
import com.marondalgram.post.model.Post;

public class Content {
	
	//post 1��
	private Post post;
	
	private Boolean fillLike;
	
	//post - ��� n��
	private List<Comment> commentlist;
	
	//post -���ƿ� n��
	private int  likeCount;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	


	public Boolean getFillLike() {
		return fillLike;
	}

	public void setFillLike(Boolean fillLike) {
		this.fillLike = fillLike;
	}

	public List<Comment> getCommentlist() {
		return commentlist;
	}

	public void setCommentlist(List<Comment> commentlist) {
		this.commentlist = commentlist;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}


	
	
	
	

}
