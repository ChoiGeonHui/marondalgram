package com.marondalgram.like.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marondalgram.like.dao.LikeDAO;
import com.marondalgram.like.model.Like;

@Service
public class LikeBO {
	
	@Autowired
	LikeDAO likeDAO;
	
	public List<Like> selectLikeByPostId(int postId){
		return likeDAO.selectLikeByPostId(postId);
	};

	public int likeConunt(int postId) {
		return likeDAO.likeCount(postId);
	};
}
