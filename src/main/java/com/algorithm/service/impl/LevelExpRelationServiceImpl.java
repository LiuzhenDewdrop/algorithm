package com.algorithm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algorithm.entity.LevelExpRelation;
import com.algorithm.repository.LevelExpRelationRep;
import com.algorithm.service.LevelExpRelationService;

/**
 * @class: LevelExpRelationServiceImpl
 * @description: 用户等级与经验的对应关系service
 * @author Liuzhen
 */
@Service("levelExpRelationService")
public class LevelExpRelationServiceImpl implements LevelExpRelationService {
	
	@Autowired
	private LevelExpRelationRep levelExpRelationRep;
	
	@Override
	public List<LevelExpRelation> getList() {
		return levelExpRelationRep.getList();
	}
}
