package com.algorithm.service;

import java.util.List;

import com.algorithm.entity.LevelExpRelation;

/**
 * @interface: LevelExpRelationService
 * @description: 用户等级与经验的对应关系service
 * @author Liuzhen
 */
public interface LevelExpRelationService {
	
	/**
	 * @title:  getList
	 * @description: 获取用户等级-经验关系列表
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-6 13:56
	 */
	List<LevelExpRelation> getList();
}
