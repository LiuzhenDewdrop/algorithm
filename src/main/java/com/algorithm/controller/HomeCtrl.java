package com.algorithm.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.algorithm.service.ConfigService;
import com.algorithm.service.SubjectService;
import com.algorithm.service.UserService;
import com.algorithm.util.BaseUtil;

/**
 * @class: HomeCtrl
 * @description: 主页ctrl
 * @author Liuzhen
 */
@Controller
public class HomeCtrl {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private ConfigService configService;
	
	/**
	 * @title:  homePage
	 * @description: 主页
	 * @param model
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-10 14:00
	 */
	@RequestMapping("/home")
	public String homePage(Model model) {
		// 获取首页用户
		model.addAttribute("users", userService.getList4Home());
		// 获取首页题目占比
		model.addAttribute("subCount", subjectService.getCount4Home());
		return "index";
	}
	
	/**
	 * @title:  setRatio
	 * @description: 设置得分倍率
	 * @param ratio
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-10 14:00
	 */
	@RequestMapping("/ratio")
	@ResponseBody
	public String setRatio(BigDecimal ratio, HttpServletRequest request) {
		if (ratio != null && ratio.compareTo(BaseUtil.getScoreRatio(request.getServletContext())) == 0) {
			return "与当前系统倍率一致，无需设置";
		}
		if (ratio.compareTo(new BigDecimal(0)) < 0 || ratio.compareTo(new BigDecimal(3)) > 0) {
			return "设置倍率错误，请输入0-3之间的数字";
		}
		configService.setRatio(ratio, request.getServletContext());
		return "设置成功，当前系统倍率为" + ratio;
	}
}
