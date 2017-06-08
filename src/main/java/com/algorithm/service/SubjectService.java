package com.algorithm.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.algorithm.entity.Subject;

/**
 * @interface: SubjectService
 * @description: 题干service
 * @author Liuzhen
 */
public interface SubjectService {
	
	/**
	 * @title:  getCount4Home
	 * @description: 获取首页的题目分类计数
	 * @return
	 * @author: Liuzhen
	 * @date:   2017-2-24 13:47
	 */
	Map<String, BigDecimal> getCount4Home();
	
	/**
	 * @title:  getList
	 * @description: 获取题目列表
	 * @param index		难度
	 * @param userLevel	用户等级
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-1 17:46
	 */
	List<Subject> getList(Integer index, String userLevel);
	
	/**
	 * @title:  getSubById
	 * @description: 根据主键获取题目信息
	 * @param id
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-2 10:51
	 */
	Subject getSubById(Integer id);
	
	/**
	 * @title:  addSubject
	 * @description: 添加题目
	 * @param subject
	 * @author: Liuzhen
	 * @date:   2017-3-10 11:36
	 */
	void addSubject(Subject subject);
	
	/**
	 * @title:  updateSubMode
	 * @description: 更新题目模式[废弃:0, 普通:1, 竞赛:2]
	 * @param id
	 * @param subMode
	 * @author: Liuzhen
	 * @date:   2017-3-10 14:34
	 */
	String updateSubMode(Integer id, Integer subMode);
	
	/**
	 * @title:  getHistoryList
	 * @description: 获取历史列表
	 * @return
	 * @author: Liuzhen
	 * @date:   2017-3-10 15:47
	 */
	List<Subject> getHistoryList();
}
