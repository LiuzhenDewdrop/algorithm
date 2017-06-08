package com.algorithm.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.algorithm.entity.User;
import com.algorithm.service.LoginService;
import com.algorithm.service.UserService;
import com.algorithm.util.BaseUtil;
import com.algorithm.util.MD5;
import com.algorithm.util.SysProp;
import com.minxin.base.common.utils.StringUtil;

/**
 * @class: LoginCtrl
 * @description: 登录ctrl
 * @author Liuzhen
 */
@Controller
public class LoginCtrl {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * @title:  validate
	 * @description: 验证登录信息
	 * @param loginName
	 * @param loginPsw
	 * @param request
	 * @param response
	 * @param model
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-2-27 11:05
	 */
	@RequestMapping("/validate")
	@ResponseBody
	public String validate(@RequestParam(required = false) String loginName, @RequestParam(required = false) String loginPsw, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtil.isBlank(loginName) || StringUtil.isBlank(loginPsw)) {
			return "帐号或密码为空";
		}
		// 查看用户名密码是否正确
		if (!loginService.validate(loginName, MD5.encrypt(loginPsw))) {
			return "帐号/密码错误";
		}
		User user = userService.getUserByLogin(loginName, MD5.encrypt(loginPsw));
		user.setLoginIP(BaseUtil.getIp(request));
		user.setLoginTime(new Date());
		user.setLogin(true);
		BaseUtil.setSessionUser(user);
		return "1";
	}
	
	/**
	 * @title:  login
	 * @description: 跳转到登录页面
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-2-27 18:03
	 */
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	/**
	 * @title:  logout
	 * @description: 登出
	 * @param request
	 * @param response
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-2-28 16:24
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute(SysProp.LOGIN_USER);
		return "login";
	}
}
