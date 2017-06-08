package com.algorithm.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.algorithm.entity.Exam;
import com.algorithm.entity.Subject;
import com.algorithm.entity.User;
import com.algorithm.entity.UserAnswer;
import com.algorithm.exception.CalcRuntimeException;
import com.algorithm.service.SubjectService;
import com.algorithm.service.UserAnswerService;
import com.algorithm.util.BaseUtil;
import com.algorithm.util.Calculator;
import com.minxin.base.common.utils.StringUtil;

/**
 * @class: SubjectCtrl
 * @description: 题目ctrl
 * @author Liuzhen
 */
@Controller
@RequestMapping("/library")
public class SubjectCtrl {
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private UserAnswerService userAnswerService;
	
	/**
	 * @title:  getList
	 * @description: 获取题目列表
	 * @param index
	 * @param model
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-2 10:09
	 */
	@RequestMapping("")
	public String getList(@RequestParam(required = false) Integer index , Model model) {
		model.addAttribute("level", index);
		model.addAttribute("subList", subjectService.getList(index, BaseUtil.getSessionUser().getUserLevel()));
		return "library";
	}
	
	/**
	 * @title:  toAnswerBook
	 * @description: 前往答题页面
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-2 10:12
	 */
	@RequestMapping("/toAnswerBook")
	public String toAnswerBook(Integer id, Model model) {
		Subject subject = subjectService.getSubById(id);
		User loginUser = BaseUtil.getSessionUser();
		// 答题者和出题者在答题的时候受到等级限制
		if ((loginUser.getRoleId() == 2 || loginUser.getRoleId() == 3) && (subject.getSubLevel().byteValue() - Byte.parseByte(loginUser.getUserLevel()) > 3)) {
			return "login";
		}
		model.addAttribute("subject", subject);
		UserAnswer ua = userAnswerService.getUserAnswer(loginUser.getId(), id);
		if (ua !=null && StringUtil.isNoneBlank(ua.getAnswerCode())) {
			try {
				ua.setAnswerCode(URLEncoder.encode(ua.getAnswerCode(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("userAnswer", ua);
		return StringUtil.isNoneBlank(subject.getSubType()) ? "answer_book" + subject.getSubType() : "library";
	}
	
	/**
	 * @title:  calculate
	 * @description: 运行代码
	 * @param exam
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-2 13:34
	 */
	@RequestMapping("/calculate")
	@ResponseBody
	public Exam calculate(Exam exam) {
		Exam result = new Exam();
		try {
			result = new Calculator().excute(exam);
		} catch (IOException e) {
			e.printStackTrace();
			result.setErrMsg("系统IO异常");
		}
		return result;
	}
	
	/**
	 * @title:  toAdd
	 * @description: 前往新增页面
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-9 14:36
	 */
	@RequestMapping("/toAdd")
	public String toAdd() {
		return (BaseUtil.getSessionUser().getRoleId() == 1 || BaseUtil.getSessionUser().getRoleId() == 3) ? "new_subject" : "login";
	}
	
	/**
	 * @title:  addSubject
	 * @description: 新增题目及其答案
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-9 14:36
	 */
	@RequestMapping("/add")
	@ResponseBody
	public String addSubject(Subject subject) {
		subjectService.addSubject(subject);
		return "1";
	}
	
	/**
	 * @title:  updateSubMode
	 * @description: 更新题目模式[废弃:0, 普通:1, 竞赛:2]
	 * @param id
	 * @param subMode
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-10 14:40
	 */
	@RequestMapping("/updateMode")
	@ResponseBody
	public String updateSubMode(Integer id, Integer subMode) {
		return subjectService.updateSubMode(id, subMode);
	}
	
	/**
	 * @title:  getHistoryList
	 * @description: 获取答题历史列表
	 * @param model
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-10 15:45
	 */
	@RequestMapping("/history")
	public String getHistoryList(Model model) {
		model.addAttribute("subList", subjectService.getHistoryList());
		return "library_history";
	}
	
	/**
	 * @title:  toHistoryBook
	 * @description: 查看个人答题历史
	 * @param id
	 * @param model
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-10 16:11
	 */
	@RequestMapping("/toHistoryBook")
	public String toHistoryBook(Integer id, Model model) {
		UserAnswer ua = userAnswerService.getUserAnswer(BaseUtil.getSessionUser().getId(), id);
		if (ua == null || !ua.getStatus()) {
			return "login";
		}
		if (StringUtil.isNoneBlank(ua.getAnswerCode())) {
			try {
				ua.setAnswerCode(URLEncoder.encode(ua.getAnswerCode(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("userAnswer", ua);
		Subject subject = subjectService.getSubById(id);
		model.addAttribute("subject", subject);
		return StringUtil.isNoneBlank(subject.getSubType()) ? "history_book" + subject.getSubType() : "library";
	}
}
