package com.high.mapper;

import java.util.List;

import com.high.entity.User;

public interface UserMapper {
	
	
	void insertUser(User user);
	
	
	List<User> selectAllUser();
	
	/**
	 * 通过用户id获得用户信息
	 * @param userId
	 * @return
	 */
	User findUserById(String userId);
}
