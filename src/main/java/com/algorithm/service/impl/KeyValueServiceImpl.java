package com.algorithm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algorithm.entity.KeyValue;
import com.algorithm.repository.KeyValueRep;
import com.algorithm.service.KeyValueService;

/**
 * @class: KeyValueServiceImpl
 * @description: 键值对service
 * @author Liuzhen
 */
@Service("keyValueService")
public class KeyValueServiceImpl implements KeyValueService {
	
	@Autowired
	private KeyValueRep keyValueRep;
	
	@Override
	public List<KeyValue> getListByType(String typeCode) {
		return keyValueRep.getListByType(typeCode);
	}
}
