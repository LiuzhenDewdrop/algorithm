package com.algorithm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.algorithm.entity.Match;
import com.algorithm.util.BaseUtil;

/**
 * @class: MatchCtrl
 * @description: 竞赛ctrl
 * @author Liuzhen
 */
@Controller
@RequestMapping("/match")
public class MatchCtrl {
	
	@RequestMapping("")
	public String getList(Model model) {
		return "match";
	}
	
	/**
	 * @title:  toAdd
	 * @description: 跳转前往新增竞赛页面
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-4-6 14:07
	 */
	@RequestMapping("/toAdd")
	public String toAdd() {
		return BaseUtil.getSessionUser().getRoleId() == 1 ? "new_match" : "login";
	}
	
	/**
	 * @title:  add
	 * @description: 新增竞赛
	 * @param match
	 * @author: Liuzhen
	 * @date:   2017-4-6 14:30
	 */
	@RequestMapping("/add")
	@ResponseBody
	public String add(Match match) {
		
		return "成功";
	}
}
