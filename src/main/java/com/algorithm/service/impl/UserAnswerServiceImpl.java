package com.algorithm.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algorithm.entity.Exam;
import com.algorithm.entity.UserAnswer;
import com.algorithm.repository.UserAnswerRep;
import com.algorithm.service.UserAnswerService;
import com.algorithm.util.BaseUtil;

/**
 * @class: UserAnswerServiceImpl
 * @description: 用户答题service
 * @author Liuzhen
 */
@Service("userAnswerService")
public class UserAnswerServiceImpl implements UserAnswerService {
	
	@Autowired
	private UserAnswerRep userAnswerRep;
	
	@Override
	public UserAnswer getUserAnswer(Integer userId, Integer subId) {
		return userAnswerRep.getUserAnswer(userId, subId);
	}
	
	
	@Override
	public void addUserAnswer(UserAnswer userAnswer) {
		userAnswerRep.addUserAnswer(userAnswer);
	}
	
	@Override
	public void updateUserAnswer(UserAnswer userAnswer) {
		userAnswerRep.updateUserAnswer(userAnswer);
	}
	
	@Override
	public void saveOrUpdateUserAnswer(UserAnswer userAnswer) {
		if (userAnswer != null && userAnswer.getId() != null) {
			userAnswerRep.updateUserAnswer(userAnswer);
			return ;
		}
		userAnswerRep.addUserAnswer(userAnswer);
	}
	
	@Override
	public void saveOrUpdateUserAnswer(Exam exam) {
		// 获取用户答题信息
		UserAnswer userAnswer = new UserAnswer();
		userAnswer.setId(exam.getUserAnswerId());
		userAnswer.setUserId(BaseUtil.getSessionUser().getId());
		userAnswer.setSubId(exam.getSubId());
		userAnswer.setAnswerCode(exam.getCode());
		userAnswer.setCreateTime(new Date());
		userAnswer.setStatus(exam.getStatus());
		saveOrUpdateUserAnswer(userAnswer);
	}
	
	@Override
	public UserAnswer getAns4Judge(Integer id) {
		return userAnswerRep.getAns4Judge(id);
	}
}
