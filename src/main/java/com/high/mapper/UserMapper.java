package com.high.mapper;

import com.high.entity.User;


public interface UserMapper {

	/**
	 * 插入一条用户信息
	 * @param user
	 */
	void insertUser(User user);
	
	/**
	 * 通过用户id获得用户信息
	 * @param userId
	 * @return
	 */
	User findUserById(String userId);

	/**
	 * 通过openId获取用户
	 * @param openId
	 * @return
	 */
	User findUserByOpenId(String openId);

	/**
	 * 更新用户的位置信息
	 * @param user
	 * @return
	 */
    boolean updateUserLocation(User user);
}
