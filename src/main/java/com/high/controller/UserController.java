package com.high.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.high.entity.User;
import com.high.exception.IdNullException;
import com.high.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller()
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/selectUser.do")
	public String selectUser(Model model) {
		List<User> list = userService.selectAllUser();
		System.out.println(list);
		model.addAttribute("allUser", list);
		return "index";
	}

	@RequestMapping("/addUser.do")
	public String addUser(User user) {
		System.out.println(user);
		userService.addUser(user);
		return "index";
	}

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @throws IOException
	 * @throws ServletException 
	 */
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request, HttpServletResponse response, User user,@RequestParam("photo") MultipartFile photo) throws IOException, ServletException {
		try{
            User findUser = userService.login(user);
            request.getSession().setAttribute("user", findUser);
            HttpSession session = request.getSession();
            String path = (String) session.getAttribute("prePath");
            return "index";
		} catch (IdNullException e) {
			e.printStackTrace();
			System.out.println(request.getContextPath());
		}
		response.sendRedirect(request.getContextPath() + "/login.jsp");
		return null;
	}

	/**
	 * 通过用户id查看该用户是否已经注册
	 * @param userId
	 * @return
	 */
	@RequestMapping("isRegister.do")
	public @ResponseBody Boolean isRegister(@RequestParam("userId") String userId){
		User user = userService.findUserById(userId);
		if(user != null)
			return true;
		return false;
	}

	/**
	 * 首先获得从前台上传的头像信息，然后注册用户，再登录
	 * @param request
	 * @param response
	 * @param user
	 * @param photo
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("registerAndLogin.do")
	public String registerAndLogin(HttpServletRequest request, HttpServletResponse response, User user,@RequestParam("photo") MultipartFile photo) throws IOException {
        if (!photo.isEmpty()) {
            String originalFilename = photo.getOriginalFilename();
            String postfix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String photoName = UUID.randomUUID().toString();
            photoName = photoName+postfix;
            File imageFile = new File(request.getServletContext().getRealPath("/images"), photoName);
            photo.transferTo(imageFile);
            user.setUserPhoto(imageFile.getPath());
            try {
                User findUser = userService.registerAndLogin(user);
                request.getSession().setAttribute("user", findUser);
            } catch (IdNullException e) {
                e.printStackTrace();
            }
            return "index";
        }
		response.sendRedirect(request.getContextPath() + "/login.jsp");
		return null;
	}
}
