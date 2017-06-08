package com.algorithm.controller;

import java.io.IOException;

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

/**
 * @class: UserCtrl
 * @description: 用户Ctrl
 * @author Liuzhen
 */
@Controller
@RequestMapping("/user")
public class UserCtrl {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LoginService loginService;
	
	/**
	 * @title:  getList
	 * @description: 获取用户列表
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-2 10:09
	 */
	@RequestMapping("")
	public String getList(Model model) {
		model.addAttribute("userList", userService.getList());
		return "top_list";
	}
	
	/**
	 * @title:  toUserInfo
	 * @description: 跳转至用户信息界面
	 * @param id
	 * @param model
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-2 9:29
	 */
	@RequestMapping("/toUserInfo")
	public String toUserInfo(@RequestParam(required = false) Integer id, Model model) {
		// 判断是否查找当前登录用户
		boolean isSelf = id == null || id == 0 || id == BaseUtil.getSessionUser().getId();
		model.addAttribute("user", isSelf ? BaseUtil.getSessionUser() : userService.getInfoById(id));
		model.addAttribute("isSelf", isSelf);
		model.addAttribute("historys", userService.getUserHistoryList(isSelf? BaseUtil.getSessionUser().getId() : id));
		return "user_info";
	}
	
	/**
	 * @title:  toUserEdit
	 * @description: 前往用户编辑页面
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-2 10:09
	 */
	@RequestMapping("/toUserEdit")
	public String toUserEdit() {
		return "user_edit";
	}
	
	/**
	 * @title:  editUser
	 * @description: 编辑用户信息
	 * @param user
	 * @param response
	 * @return
	 * @author: Liuzhen
	 * @date:   2017-3-2 10:10
	 */
	@RequestMapping("/edit")
	public void editUser(User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
		userService.updateLoginUser(user, request.getServletContext());
		response.sendRedirect("/user/toUserInfo");
	}
	
	/**
	 * @title:  checkPsw
	 * @description: 检查登录密码是否正确
	 * @param loginPsw
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-2 10:10
	 */
	@RequestMapping("/checkPsw")
	@ResponseBody
	public Integer checkPsw(@RequestParam(required = true) String loginPsw) {
		// 查看用户名密码是否正确
		if (!loginService.validate(BaseUtil.getSessionUser().getLoginName(), MD5.encrypt(loginPsw))) {
			return 0;
		}
		return 1;
	}
	
	/**
	 * @title:  toChangePassword
	 * @description: 前往修改密码页面
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-2 10:10
	 */
	@RequestMapping("/toChangePsw")
	public String toChangePassword() {
		return "user_pwd";
	}
	
	/**
	 * @title:  changePassword
	 * @description: 修改密码
	 * @param oldPassword 原始密码
	 * @param newPassword 新密码
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-2 10:11
	 */
	@RequestMapping("/changePsw")
	@ResponseBody
	public String changePassword(String oldPassword, String newPassword) {
		if (!loginService.validate(BaseUtil.getSessionUser().getLoginName(), MD5.encrypt(oldPassword))) {
			return "原始密码验证错误!";
		}
		userService.updatePsw(BaseUtil.getSessionUser().getId(), MD5.encrypt(newPassword));
		return "1";
	}
	
	/**
	 * @title:  changeUser
	 * @description: 更改用户状态
	 * @param userId
	 * @param index		1、管理员; 2答题者; 3、出题者; 4、判题者; 5、参赛者; 6、启用; 0、禁用。
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-9 17:53
	 */
	@RequestMapping("/changeUser")
	@ResponseBody
	public String changeUser(Integer userId, Integer index) {
		if (BaseUtil.getSessionUser().getRoleId() != 1) {
			return "您无此权限";
		}
		if (userId == null) {
			return "未选择用户";
		}
		if (index == null) {
			return "无效操作";
		}
		return userService.updateUserRA(userId, index);
	}
	
	/**
	 * @title:  setDftPsw
	 * @description: 重置密码
	 * @param userId
	 * @param psw
	 * @return
	 * @author: Liuzhen
	 * @date:   2017-3-10 16:34
	 */
	@RequestMapping("/setDftPsw")
	@ResponseBody
	public String setDftPsw(Integer userId, String psw) {
		if (BaseUtil.getSessionUser().getRoleId() != 1) {
			return "你不是管理员，别捣乱！";
		}
		User user = userService.getInfoById(userId);
		if (user == null) {
			return "查无此用户";
		}
		userService.updatePsw(userId, MD5.encrypt(psw));
		return "更新密码成功，已将用户" + user.getUserName() + "的密码更新为初始密码：" + psw + "，请通知其尽快修改！";
	}
}
