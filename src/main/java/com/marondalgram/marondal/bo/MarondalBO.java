package com.marondalgram.marondal.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marondalgram.marondal.dao.MarondalDAO;
import com.marondalgram.marondal.model.User;

@Service
public class MarondalBO {
	
	@Autowired
	MarondalDAO marondalDAO;
	
	public User selectUserById(String loginId) {
		return marondalDAO.selectUserById(loginId);
	}
	
	public User getUserById(String loginId,String passowrd) {
		return marondalDAO.getUserById(loginId,passowrd);
	}
	
	
	public void insertUser(String loginId,
			String password,
			String name,
			String email) {
		marondalDAO.insertUser(loginId, password, name, email);
	}

}
