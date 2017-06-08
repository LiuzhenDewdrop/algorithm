package com.algorithm.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.algorithm.entity.Answer;

/**
 * @interface: AnswerRep
 * @description: 答案rep
 * @author Liuzhen
 */
public interface AnswerRep {
	
	/**
	 * @title:  getListBySub
	 * @description: 根据题目获取答案列表
	 * @param subId
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-3 12:12
	 */
	List<Answer> getListBySub(@Param("subId") Integer subId);
	
	/**
	 * @title:  addAnswers
	 * @description: 添加答案
	 * @param answerList
	 * @author: Liuzhen
	 * @date:   2017-3-9 16:49
	 */
	void addAnswers(List<Answer> answerList);
}
