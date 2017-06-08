package com.algorithm.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.algorithm.entity.Judgement;

/**
 * @interface: JudgementRep
 * @description: 阅卷rep
 * @author Liuzhen
 */
public interface JudgementRep {
	
	/**
	 * @title:  addJudgements
	 * @description: 添加判题记录
	 * @param judgementList
	 * @author: Liuzhen
	 * @date:   2017-3-3 14:43
	 */
	void addJudgements(List<Judgement> judgementList);
	
	/**
	 * @title:  countJudgements
	 * @description: 判断该用户这道题是否已阅卷
	 * @param userId
	 * @param subId
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-10 13:45
	 */
	Integer countJudgements(@Param("userId") Integer userId, @Param("subId") Integer subId);
}
