package com.algorithm.service;

import java.util.List;

import com.algorithm.entity.KeyValue;

/**
 * @interface: KeyValueService
 * @description: 键值对service
 * @author Liuzhen
 */
public interface KeyValueService {
	
	/**
	 * @title:  getListByType
	 * @description: 根据类型获取键值对列表
	 * @param typeCode
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-8 12:10
	 */
	List<KeyValue> getListByType(String typeCode);
}
