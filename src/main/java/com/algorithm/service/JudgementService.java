package com.algorithm.service;

import javax.servlet.ServletContext;

import com.algorithm.entity.Exam;
import com.algorithm.entity.Judgement;
import com.algorithm.exception.CalcRuntimeException;

/**
 * @interface: JudgementService
 * @description: 判题service
 * @author Liuzhen
 */
public interface JudgementService {
	
	/**
	 * @title:  saveJudgement
	 * @description: 判题
	 * @param exam
	 * @param sc
	 * @return
	 * @throws CalcRuntimeException
	 * @author: Liuzhen
	 * @date:   2017-3-3 18:48
	 */
	String saveJudgement(Exam exam, ServletContext sc) throws CalcRuntimeException;
	
	/**
	 * @title:  addJudgement
	 * @description: 增加判题记录
	 * @param judgement
	 * @param sc
	 * @author: Liuzhen
	 * @date:   2017-3-10 12:56
	 */
	void addJudgement(Judgement judgement, ServletContext sc);
	
	/**
	 * @title:  hasJudged
	 * @description: 判断该用户这道题是否已阅卷
	 * @param userId
	 * @param subId
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-10 13:44
	 */
	boolean hasJudged(Integer userId, Integer subId);
}
