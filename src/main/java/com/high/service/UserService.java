package com.high.service;

import com.high.entity.User;

public interface UserService {

	void addUser(User user);

	User findUserById(String userId);

    User findUserByOpenId(String openId);

    User register(User user);

	boolean updateUserLocation(User user);
}
