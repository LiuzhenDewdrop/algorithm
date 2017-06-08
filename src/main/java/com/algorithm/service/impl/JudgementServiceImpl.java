package com.algorithm.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algorithm.entity.Answer;
import com.algorithm.entity.Exam;
import com.algorithm.entity.Judgement;
import com.algorithm.entity.LevelExpRelation;
import com.algorithm.entity.Subject;
import com.algorithm.entity.User;
import com.algorithm.entity.UserHistory;
import com.algorithm.exception.CalcRuntimeException;
import com.algorithm.repository.AnswerRep;
import com.algorithm.repository.JudgementRep;
import com.algorithm.repository.UserHistoryRep;
import com.algorithm.service.JudgementService;
import com.algorithm.service.SubjectService;
import com.algorithm.service.UserAnswerService;
import com.algorithm.service.UserService;
import com.algorithm.util.BaseUtil;
import com.algorithm.util.Calculator;
import com.algorithm.util.SysProp;

/**
 * @class: JudgementServiceImpl
 * @description: 判题service
 * @author Liuzhen
 */
@Service("judgementService")
public class JudgementServiceImpl implements JudgementService {
	
	@Autowired
	private UserAnswerService userAnswerService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private AnswerRep answerRep;
	
	@Autowired
	private JudgementRep judgementRep;
	
	@Autowired
	private UserHistoryRep userHistoryRep;
	
	@Autowired
	private UserService userService;
	
	@Override
	public String saveJudgement(Exam exam, ServletContext sc) throws CalcRuntimeException {
		// 处理用户答题信息
		userAnswerService.saveOrUpdateUserAnswer(exam);
		// 如果是暂存, 则提示信息
		if (!exam.getStatus()) {
			return "暂存成功";
		}
		// 如果是交卷, 则开始判题
		// 主观题
		if ("3".equals(exam.getSubType())) {
			return "主观题提交成功, 请等待判卷";
		}
		Subject subject = subjectService.getSubById(exam.getSubId());
		User loginUser = BaseUtil.getSessionUser();
		Integer score = subject.getSubMode() == 2 ? new BigDecimal(subject.getSubScore()).multiply((BigDecimal) sc.getAttribute(SysProp.SCORE_RATIO)).intValue() : new BigDecimal(Calculator.calcScore(Byte.parseByte(loginUser.getUserLevel()), subject.getSubLevel(), subject.getSubScore())).multiply((BigDecimal) sc.getAttribute(SysProp.SCORE_RATIO)).intValue();
		// 答案列表
		List<Answer> ansList = answerRep.getListBySub(exam.getSubId());
		List<Judgement> judgementList = new ArrayList<Judgement>(ansList.size());
		// 准备返回值所需数据
		int total = 0;		// 总测试用例数
		int pass = 0;		// 通过的测试用例数
		int totalScore = 0;	// 总得分
		// 客观题
		if ("2".equals(exam.getSubType())) {
			Answer answer = ansList.get(0);
			Judgement judgement = new Judgement();
			judgement.setUserId(loginUser.getId());
			judgement.setUserLevel(loginUser.getUserLevel());
			judgement.setSubId(exam.getSubId());
			judgement.setAnsId(answer.getId());
			boolean correct = answer.getAnsOutput().equals(exam.getCode());
			judgement.setCorrect(correct);
			judgement.setScore(correct ? score : 0);
			judgement.setExaminerId(0);
			judgement.setCreateTime(new Date());
			judgementList.add(judgement);
			total = 1;
			pass = correct ? 1 : 0;
			totalScore = correct ? score : 0;
			// 编程题
		} else if ("1".equals(exam.getSubType())) {
			total = ansList.size();
			for (int i = 0; i < ansList.size(); i++) {
				Answer answer = ansList.get(i);
				Judgement judgement = new Judgement();
				judgement.setUserId(loginUser.getId());
				judgement.setUserLevel(loginUser.getUserLevel());
				judgement.setSubId(exam.getSubId());
				judgement.setAnsId(answer.getId());
				judgement.setExaminerId(0);
				judgement.setCreateTime(new Date());
				exam.setInput(answer.getAnsInput());
				try {
					Exam calcResult = new Calculator().excute(exam);
					boolean correct = answer.getAnsOutput().equals(calcResult.getOutput());
					judgement.setCorrect(correct);
					judgement.setScore(correct ? Math.round(score * answer.getProportion()) : 0);
					judgement.setRunTime(calcResult.getRunTime());
					pass += correct ? 1 : 0;
					totalScore += judgement.getScore();
				} catch (IOException e) {
					throw new CalcRuntimeException();
				}
				judgementList.add(judgement);
			} // for.
		}
		// 添加判题记录
		judgementRep.addJudgements(judgementList);
		// 计算用户等级
		boolean isLvUp = false;
		if (totalScore > 0) {
			Integer newExp = loginUser.getExperiencePoint() + totalScore;
			LevelExpRelation le = Calculator.calcUserLevel(newExp, sc);
			Byte newLevel = le.getUserLevel();
			isLvUp = newLevel.compareTo(Byte.parseByte(loginUser.getUserLevel())) > 0;
			loginUser.setExperiencePoint(newExp);
			if (isLvUp) {
				loginUser.setUserLevel(newLevel + "");
				UserHistory userHistory = new UserHistory();
				userHistory.setUserId(loginUser.getId());
				userHistory.setContent("等级提升, 达到" + newLevel + "; 获得荣誉称号: " + le.getLevelName() + "!");
				userHistory.setCreateTime(new Date());
				userHistoryRep.addUserHistory(userHistory);
			}
			// 更新用户等级经验信息
			userService.updateUserExp(loginUser.getId(), newLevel + "", newExp);
		}
		return "共" + total + "个测试用例, 您通过了" + pass + "个, 得" + totalScore + "分!" + (isLvUp ? "\r\n恭喜您, 升级啦~!" : "");
	}
	
	@Override
	public void addJudgement(Judgement judgement, ServletContext sc) {
		Integer score = new BigDecimal(Calculator.calcScore(Byte.parseByte(judgement.getUserLevel()), judgement.getSubLevel(), Byte.parseByte(judgement.getScore() + ""))).multiply((BigDecimal) sc.getAttribute(SysProp.SCORE_RATIO)).multiply(new BigDecimal(judgement.getRatio())).divide(new BigDecimal(100), 0, BigDecimal.ROUND_HALF_UP).intValue();
		judgement.setScore(score);
		judgement.setCorrect(score > 0);
		judgement.setExaminerId(BaseUtil.getSessionUser().getId());
		judgement.setCreateTime(new Date());
		List<Judgement> list = new ArrayList<>(1);
		list.add(judgement);
		judgementRep.addJudgements(list);
	}
	
	@Override
	public boolean hasJudged(Integer userId, Integer subId) {
		return judgementRep.countJudgements(userId, subId) > 0;
	}
}
