package com.marondalgram.timeline.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.marondalgram.post.model.Post;

@Repository
public interface TimelineDAO {
	
	public Post selectPost(int postId);
	
	public int insertPost(
			@Param("userId") int userId,
			@Param("userName") String userName,
			@Param("content") String content,
			@Param("imagePath")String imagePath);
	
	
	public void deletePost(int postId);
	
	
	
	
	public List<Post> selectListPostById();
	
	

}
