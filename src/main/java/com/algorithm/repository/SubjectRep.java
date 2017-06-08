package com.algorithm.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.algorithm.entity.Subject;

/**
 * @interface: SubjectRep
 * @description: 题目rep
 * @author Liuzhen
 */
public interface SubjectRep {
	
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
	 * @param subMode	题目模式[所有题目:0, 普通:1, 竞赛:2]
	 * @return
	 * @author: Liuzhen
	 * @date:   2017-3-1 17:45
	 */
	List<Subject> getList(@Param("index") Integer index, @Param("userLevel") String userLevel, @Param("subMode") Integer subMode);
	
	/**
	 * @title:  getSubById
	 * @description: 根据PK获取题目信息
	 * @param id
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-2 10:41
	 */
	Subject getSubById(@Param("id") Integer id);
	
	/**
	 * @title:  addSubject
	 * @description: 添加新题目
	 * @param subject
	 * @author: Liuzhen
	 * @date:   2017-3-9 14:26
	 */
	void addSubject(Subject subject);
	
	/**
	 * @title:  getList4Judge
	 * @description: 获取待判题列表
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-10 11:19
	 */
	List<Subject> getList4Judge();
	
	/**
	 * @title:  updateSubMode
	 * @description: 更新题目模式[废弃:0, 普通:1, 竞赛:2]
	 * @param id
	 * @param subMode
	 * @author: Liuzhen
	 * @date:   2017-3-10 14:33
	 */
	void updateSubMode(@Param("id") Integer id, @Param("subMode") Integer subMode);
	
	/**
	 * @title:  getHistoryList
	 * @description: 获取历史列表
	 * @param userId
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-10 15:46
	 */
	List<Subject> getHistoryList(@Param("userId") Integer userId);
}
