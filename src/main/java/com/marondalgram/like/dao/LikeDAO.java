package com.marondalgram.like.dao;



import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.marondalgram.like.model.Like;

@Repository
public interface LikeDAO {
	
	public int fillLikeByPostIdAndUserId(@Param("userId") int userId,	
			@Param("postId") int postId);
	
	
	public int likeCount(@Param("postId") int postId);

	//���ƿ� ���
	public int insertLike(@Param("userId") int userId,
			@Param("postId") int postId);
	
	//���ƿ����
	public int deleteLike(@Param("userId") int userId,
			@Param("postId") int postId);
	
	
	//������ �Խù� ���ƿ� ����
	public int deletePostLike(int postId);
	
	
	public Like checklikeByUserId(@Param("userId") int userId);
}
