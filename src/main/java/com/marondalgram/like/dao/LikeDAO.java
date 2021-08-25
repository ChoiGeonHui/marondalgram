package com.marondalgram.like.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.marondalgram.like.model.Like;

@Repository
public interface LikeDAO {
	
	public List<Like> selectLikeByPostId(		
			@Param("postId") int postId);
	
	
	public int likeCount(@Param("postId") int postId);

}
