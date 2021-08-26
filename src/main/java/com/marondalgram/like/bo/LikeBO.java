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

	//���ƿ� ����
	public int likeConunt(int postId) {
		return likeDAO.likeCount(postId);
	};
	
	
	//���ƿ� �߰�
	public int insertLike(int userId,int postId) {
		return likeDAO.insertLike(userId, postId);
	}
	
	
	
	//���ƿ� ���
	public int deleteLike(int userId,int postId) {
		return likeDAO.deleteLike(userId, postId);
	}
	
	
	//������ �Խù� ���ƿ����
	public int deletePostLike(int postId) {
		return likeDAO.deletePostLike(postId);
	}
	
	
	
	//���ƿ� Ȯ�� ����
	public Like checkLike(int userId) {
		return likeDAO.checklikeByUserId(userId);
	}
	
}
