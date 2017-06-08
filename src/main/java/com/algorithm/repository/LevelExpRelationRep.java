package com.algorithm.repository;

import java.util.List;

import com.algorithm.entity.LevelExpRelation;

/**
 * @interface: LevelExpRelationRep
 * @description: 用户等级与经验的对应关系rep
 * @author Liuzhen
 */
public interface LevelExpRelationRep {
	
	/**
	 * @title:  getList
	 * @description: 获取用户等级-经验关系列表
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-6 10:27
	 */
	List<LevelExpRelation> getList();
}
