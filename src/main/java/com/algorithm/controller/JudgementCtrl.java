package com.algorithm.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.algorithm.entity.Exam;
import com.algorithm.entity.Judgement;
import com.algorithm.entity.UserAnswer;
import com.algorithm.exception.CalcRuntimeException;
import com.algorithm.service.JudgementService;
import com.algorithm.service.SubjectService;
import com.algorithm.service.UserAnswerService;
import com.algorithm.util.BaseUtil;

/**
 * @class: JudgementCtrl
 * @description: 判题ctrl
 * @author Liuzhen
 */
@Controller
@RequestMapping("/judge")
public class JudgementCtrl {
	
	@Autowired
	private UserAnswerService userAnswerService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private JudgementService judgementService;
	
	/**
	 * @title:  judge
	 * @description: 判题
	 * @param exam
	 * @param request
	 * @return
	 * @author: Liuzhen
	 * @date:   2017-3-9 14:36
	 */
	@RequestMapping("")
	@ResponseBody
	public String judge(Exam exam, HttpServletRequest request) {
		// 先判断该用户是否答过该题
		UserAnswer userAnswer = userAnswerService.getUserAnswer(BaseUtil.getSessionUser().getId(), exam.getSubId());
		if (userAnswer != null) {
			if(userAnswer.getStatus()) {
				return "您已答过该题";
			}
			exam.setUserAnswerId(userAnswer.getId());
		}
		try {
			return judgementService.saveJudgement(exam, request.getServletContext());
		} catch (CalcRuntimeException c) {
			return "系统IO异常";
		}
	}
	
	/**
	 * @title:  toJudgement
	 * @description: 前往判题页面
	 * @param id
	 * @return
	 * @author: Liuzhen
	 * @date:   2017-3-10 11:38
	 */
	@RequestMapping("/toAdd")
	public String toJudgement(Integer id, Model model) {
		model.addAttribute("userAnswer", userAnswerService.getAns4Judge(id));
		return "judgement";
	}
	
	/**
	 * @title:  addJudgement
	 * @description: 增加判题记录
	 * @param judgement
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-10 12:53
	 */
	@RequestMapping("/add")
	@ResponseBody
	public String addJudgement(Judgement judgement, HttpServletRequest request) {
		if (judgementService.hasJudged(judgement.getUserId(), judgement.getSubId())) {
			return "该题已阅卷";
		}
		judgementService.addJudgement(judgement, request.getServletContext());
		return "判题成功!";
	}
}
