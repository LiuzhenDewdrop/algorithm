package com.algorithm.repository;

import org.apache.ibatis.annotations.Param;

import com.algorithm.entity.UserAnswer;

/**
 * @interface: UserAnswerRep
 * @description: 用户答题记录rep
 * @author Liuzhen
 */
public interface UserAnswerRep {
	
	/**
	 * @title:  getUserAnswer
	 * @description: 根据用户和题目获取答题信息
	 * @param userId
	 * @param subId
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-3 12:25
	 */
	UserAnswer getUserAnswer(@Param("userId") Integer userId, @Param("subId") Integer subId);
	
	/**
	 * @title:  addUserAnswer
	 * @description: 添加用户答题记录
	 * @param userAnswer
	 * @author: Liuzhen
	 * @date:   2017-3-3 12:41
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
	 * @title:  getAns4Judge
	 * @description: 获取待判题信息
	 * @param id
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-10 11:59
	 */
	UserAnswer getAns4Judge(@Param("id") Integer id);
}
