package com.marondalgram.like.bo;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marondalgram.like.dao.LikeDAO;
import com.marondalgram.like.model.Like;

@Service
public class LikeBO {
	
	@Autowired
	LikeDAO likeDAO;
	
	public boolean fillLikeByPostIdAndUserId(Integer userId,int postId){
		
		if(userId ==null) {
			return false;
		}
		
		int cnt =likeDAO.fillLikeByPostIdAndUserId(userId,postId);
		
		
		
		return cnt>0? true:false;
	};

	//좋아요 갯수
	public int likeConunt(int postId) {
		return likeDAO.likeCount(postId);
	};
	
	
	//좋아요 추가
	public int insertLike(int userId,int postId) {
		return likeDAO.insertLike(userId, postId);
	}
	
	
	
	//좋아요 취소
	public int deleteLike(int userId,int postId) {
		return likeDAO.deleteLike(userId, postId);
	}
	
	
	//삭제된 게시물 좋아요삭제
	public int deletePostLike(int postId) {
		return likeDAO.deletePostLike(postId);
	}
	
	
	
	//좋아요 확인 여부
	public Like checkLike(int userId) {
		return likeDAO.checklikeByUserId(userId);
	}
	
}
