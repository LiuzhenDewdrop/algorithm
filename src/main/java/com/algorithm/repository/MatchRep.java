package com.algorithm.repository;

import java.util.List;

import com.algorithm.entity.Match;

/**
 * @interface: MatchRep
 * @description: 竞赛rep
 * @author Liuzhen
 */
public interface MatchRep {
	
	/**
	 * @title:  getList
	 * @description: 获取竞赛列表
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-4-6 14:46
	 */
	List<Match> getList();
	
	/**
	 * @title:  addMatch
	 * @description: 添加新竞赛
	 * @param match
	 * @author: Liuzhen
	 * @date:   2017-4-6 14:36
	 */
	void addMatch(Match match);
}
