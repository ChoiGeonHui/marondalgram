package com.marondalgram.marondal.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.marondalgram.marondal.model.User;

@Repository
public interface MarondalDAO {
	
	public User selectUserById(String loginId);
	
	public User getUserById(@Param("loginId") String loginId,
			@Param("password") String password);
	
	public void insertUser(@Param ("loginId")String loginId,
			@Param ("password") String password,
			@Param ("name") String name,
			@Param ("email") String email);

}
