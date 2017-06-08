package com.algorithm.service;

import com.algorithm.entity.Exam;
import com.algorithm.entity.UserAnswer;

/**
 * @interface: UserAnswerService
 * @description: 用户答题service
 * @author Liuzhen
 */
public interface UserAnswerService {
	
	/**
	 * @title:  getUserAnswer
	 * @description: 根据用户和题目获取答题信息
	 * @param userId
	 * @param subId
	 * @return
	 * @author: Liuzhen
	 * @date:   2017-3-3 12:27
	 */
	UserAnswer getUserAnswer(Integer userId, Integer subId);
	
	/**
	 * @title:  addUserAnswer
	 * @description: 添加用户答题记录
	 * @param userAnswer
	 * @author: Liuzhen
	 * @date:   2017-3-3 12:46
	 */
	void addUserAnswer(UserAnswer userAnswer);
	
	/**
	 * @title:  updateUserAnswer
	 * @description: 更新用户答题记录
	 * @param userAnswer
	 * @author: Liuzhen
	 * @date:   2017-3-3 12:57
	 */
	void updateUserAnswer(UserAnswer userAnswer);
	
	/**
	 * @title:  saveOrUpdateUserAnswer
	 * @description: 新增/保存用户答题信息
	 * @param userAnswer
	 * @author: Liuzhen
	 * @date:   2017-3-3 13:04
	 */
	void saveOrUpdateUserAnswer(UserAnswer userAnswer);
	
	/**
	 * @title:  saveOrUpdateUserAnswer
	 * @description: 新增/保存用户答题信息
	 * @param exam
	 * @author: Liuzhen
	 * @date:   2017-3-3 13:54
	 */
	void saveOrUpdateUserAnswer(Exam exam);
	
	/**
	 * @title:  getAns4Judge
	 * @description: 获取待判题信息
	 * @param id
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-10 12:01
	 */
	UserAnswer getAns4Judge(Integer id);
}
