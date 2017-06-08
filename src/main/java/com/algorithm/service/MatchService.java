package com.algorithm.service;

import com.algorithm.entity.Match;

/**
 * @interface: MatchService
 * @description: 竞赛service
 * @author Liuzhen
 */
public interface MatchService {
	
	/**
	 * @title:  addMatch
	 * @description: 添加新竞赛
	 * @param match
	 * @author: Liuzhen
	 * @date:   2017-4-6 14:36
	 */
	void addMatch(Match match);
}
