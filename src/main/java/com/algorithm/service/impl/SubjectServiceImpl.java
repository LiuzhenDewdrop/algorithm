package com.algorithm.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algorithm.entity.Answer;
import com.algorithm.entity.Exam;
import com.algorithm.entity.Judgement;
import com.algorithm.entity.LevelExpRelation;
import com.algorithm.entity.Subject;
import com.algorithm.entity.User;
import com.algorithm.entity.UserAnswer;
import com.algorithm.entity.UserHistory;
import com.algorithm.exception.CalcRuntimeException;
import com.algorithm.repository.AnswerRep;
import com.algorithm.repository.JudgementRep;
import com.algorithm.repository.SubjectRep;
import com.algorithm.repository.UserAnswerRep;
import com.algorithm.repository.UserHistoryRep;
import com.algorithm.service.SubjectService;
import com.algorithm.service.UserAnswerService;
import com.algorithm.service.UserService;
import com.algorithm.util.BaseUtil;
import com.algorithm.util.Calculator;
import com.algorithm.util.SysProp;

/**
 * @class: SubjectServiceImpl
 * @description: 题目service
 * @author Liuzhen
 */
@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {
	
	@Autowired
	private SubjectRep subjectRep;
	
	@Autowired
	private AnswerRep answerRep;
	
	@Override
	public Map<String, BigDecimal> getCount4Home() {
		Map<String, BigDecimal> subCount = subjectRep.getCount4Home();
		// 处理首页占比
		if (subCount.get("total") != null && subCount.get("total").compareTo(new BigDecimal(0)) > 0) {
			subCount.put("newborn", subCount.get("newborn").multiply(new BigDecimal(100)).divide(subCount.get("total"), 2, BigDecimal.ROUND_HALF_UP));
			subCount.put("warrior", subCount.get("warrior").multiply(new BigDecimal(100)).divide(subCount.get("total"), 2, BigDecimal.ROUND_HALF_UP));
			subCount.put("hero", subCount.get("hero").multiply(new BigDecimal(100)).divide(subCount.get("total"), 2, BigDecimal.ROUND_HALF_UP));
			subCount.put("king", subCount.get("king").multiply(new BigDecimal(100)).divide(subCount.get("total"), 2, BigDecimal.ROUND_HALF_UP));
		} else {
			subCount.put("newborn", new BigDecimal(0));
			subCount.put("warrior", new BigDecimal(0));
			subCount.put("hero", new BigDecimal(0));
			subCount.put("king", new BigDecimal(0));
		}
		return subCount;
	}
	
	@Override
	public List<Subject> getList(Integer index, String userLevel) {
		switch (BaseUtil.getSessionUser().getRoleId()) {
			case 2:		//答题者
				return subjectRep.getList(index, userLevel, 1);
			case 5:		// 参赛者
				// TODO
				return subjectRep.getList(index, userLevel, 2);
			case 4:		// 判题者
				return subjectRep.getList4Judge();
			default:	// 1和3, 管理员和出题者
				return subjectRep.getList(index, userLevel, 0);
		}
	}
	
	@Override
	public Subject getSubById(Integer id) {
		return subjectRep.getSubById(id);
	}
	
	@Override
	public void addSubject(Subject subject) {
		subject.setCreateId(BaseUtil.getSessionUser().getId());
		subject.setCreateTime(new Date());
		subjectRep.addSubject(subject);
		List<Answer> answerList = subject.getAnsList();
		for (Answer ans: answerList) {
			ans.setSubId(subject.getId());
			ans.setAnsOutput(ans.getAnsOutput().trim());
		}
		if (!"1".equals(subject.getSubType())) {
			answerList.get(0).setProportion(1f);
		}
		answerRep.addAnswers(answerList);
	}
	
	@Override
	public String updateSubMode(Integer id, Integer subMode) {
		if (id <= 0) {
			return "未找到该题目";
		}
		if (subMode != 0 && subMode != 1 && subMode != 2) {
			return "错误的题目模式";
		}
		subjectRep.updateSubMode(id, subMode);
		return "更新成功";
	}
	
	@Override
	public List<Subject> getHistoryList() {
		return subjectRep.getHistoryList(BaseUtil.getSessionUser().getId());
	}
}
