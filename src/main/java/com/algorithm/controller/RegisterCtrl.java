package com.algorithm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.algorithm.entity.User;
import com.algorithm.service.RegisterService;
import com.algorithm.service.UserService;
import com.minxin.base.common.utils.StringUtil;

/**
 * @class: RegisterCtrl
 * @description: 注册ctrl
 * @author Liuzhen
 */
@Controller
@RequestMapping("/register")
public class RegisterCtrl {
	
	@Autowired
	private RegisterService registerService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * @title:  register
	 * @description: 跳转到注册页面
	 * @return
	 * @author: Liuzhen
	 * @date:   2017-2-27 18:03
	 */
	@RequestMapping("")
	public String toRegister() {
		return "register";
	}
	
	/**
	 * @title:  submitRegister
	 * @description: 提交注册信息
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-2-28 10:00
	 */
	@RequestMapping("/submit")
	@ResponseBody
	public String submitRegister(User user) {
		user.setLoginName(user.getLoginName().trim());
		if (StringUtil.isBlank(user.getLoginName())) {
			return "登录名不能为空!";
		}
		user.setUserName(user.getUserName().trim());
		if (StringUtil.isBlank(user.getUserName())) {
			return "用户名不能为空!";
		}
		userService.addUser(user);
		return "1";
	}
	
	/**
	 * @title:  checkLoginName
	 * @description: 检查登录名
	 * @param loginName
	 * @return
	 * @author: Liuzhen
	 * @date:   2017-2-28 9:27
	 */
	@RequestMapping("/checkLoginName")
	@ResponseBody
	public Integer checkLoginName(String loginName) {
		return registerService.existLoginName(loginName)? 0: 1;
	}
	
	/**
	 * @title:  checkUserName
	 * @description: 检查用户名
	 * @param userName
	 * @return
	 * @author: Liuzhen
	 * @date:   2017-2-28 9:28
	 */
	@RequestMapping("/checkUserName")
	@ResponseBody
	public Integer checkUserName(String userName) {
		return registerService.existUserName(userName)? 0: 1;
	}
}
