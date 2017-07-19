package com.high.service.impl;

import com.high.entity.Location;
import com.high.entity.User;
import com.high.mapper.UserMapper;
import com.high.service.LocationService;
import com.high.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private LocationService locationService;

	@Override
	public void addUser(User user) {
		if (null == user.getUserId()) {
			UUID uuid = UUID.randomUUID();
			String userId = uuid.toString();
			System.out.println("userId: " + userId);
			user.setUserId(userId);
		}
		userMapper.insertUser(user);
	}

	@Override
	public User findUserById(String userId) {
		return userMapper.findUserById(userId);
	}


	@Override
	public User findUserByOpenId(String openId) {
		return userMapper.findUserByOpenId(openId);
	}

	@Override
	public User register(User user) {
		if (null == user.getUserId()) {
			UUID uuid = UUID.randomUUID();
			String userId = uuid.toString();
			user.setUserId(userId);
		}
		userMapper.insertUser(user);
		return user;
	}

	@Override
	public boolean updateUserLocation(User user) {
		//获得通过用户id获得用户信息，进而处理用户原有的位置信息
		User findUser = findUserById(user.getUserId());
		if(findUser==null)
			return false;
		String locationId = findUser.getLocationId();
		Location location = user.getLocation();
		location.setLocationId(locationId);
		if(locationId !=null){
			locationService.updateLocation(location);//删除原有的位置信息
		}//给位置表里面添加一个位置信息，并更新用户的位置信息
		/*Location location = locationService.insertLocation(user.getLocation());
		user.setLocationId(location.getLocationId());*/
		return userMapper.updateUserLocation(user);

	}
}
