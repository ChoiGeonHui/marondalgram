package com.marondalgram.timeline.domain;

import java.util.List;

import com.marondalgram.comment.model.Comment;
import com.marondalgram.like.model.Like;
import com.marondalgram.post.model.Post;

public class Content {
	
	//post 1개
	private Post post;
	
	//post -좋아요 n개
	private List<Like> likelist;
	
	//post - 댓글 n개
	private List<Comment> commentlist;
	
	private int  likeCount;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public List<Like> getLikelist() {
		return likelist;
	}

	public void setLikelist(List<Like> likelist) {
		this.likelist = likelist;
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
