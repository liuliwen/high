package com.high.controller;

import com.high.entity.User;
import com.high.service.LocationService;
import com.high.service.UserService;
import com.high.utils.WeChatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller()
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private LocationService locationService;

	/**
	 * 用户登录
	 * @param user
	 * @param code
	 * @param request
	 * @return
	 */
	@RequestMapping("/login.do")
	public @ResponseBody Map<String,Object> login(User user, String code, HttpServletRequest request){
		//查询用户的opedId
		String openId = WeChatUtils.getOpedId(code);
		Map<String, Object> map = new HashMap<String, Object>();

		User u = null;
		//查看用户是否已经注册，若未注册，则注册
		u = userService.findUserByOpenId(openId);
		if(u == null){
			user.setOpenId(openId);
			u = userService.register(user);
		}
		//登录用户，将用户信息保存在session中
		request.getSession().setAttribute("user",u);

		map.put("code",200);
		map.put("userId",u.getUserId());
		return map;
	}

	@RequestMapping("/updateUserLocation.do")
	public @ResponseBody Map<String,Object> updateUserLocation(@RequestBody User user){
		Map<String, Object> map = new HashMap<String, Object>();
		if(userService.updateUserLocation(user)){
			map.put("code",200);
		}else{
			map.put("code",500);
		}
		return map;
	}
}
