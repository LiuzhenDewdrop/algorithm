package com.algorithm.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.algorithm.entity.Match;
import com.algorithm.repository.MatchRep;
import com.algorithm.service.MatchService;
import com.algorithm.util.BaseUtil;

/**
 * @class: MatchServiceImpl
 * @description: 竞赛service
 * @author Liuzhen
 */
public class MatchServiceImpl implements MatchService {
	
	@Autowired
	private MatchRep matchRep;
	
	@Override
	public void addMatch(Match match) {
		match.setCreateId(BaseUtil.getSessionUser().getId());
		match.setCreateTime(new Date());
		matchRep.addMatch(match);
	}
}
