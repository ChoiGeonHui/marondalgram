package com.marondalgram.like.dao;



import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.marondalgram.like.model.Like;

@Repository
public interface LikeDAO {
	
	public int fillLikeByPostIdAndUserId(@Param("userId") int userId,	
			@Param("postId") int postId);
	
	
	public int likeCount(@Param("postId") int postId);

	//좋아요 등록
	public int insertLike(@Param("userId") int userId,
			@Param("postId") int postId);
	
	//좋아요취소
	public int deleteLike(@Param("userId") int userId,
			@Param("postId") int postId);
	
	
	//삭제된 게시물 좋아요 삭제
	public int deletePostLike(int postId);
	
	
	public Like checklikeByUserId(@Param("userId") int userId);
}
