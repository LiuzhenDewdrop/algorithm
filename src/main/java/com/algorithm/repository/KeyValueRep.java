package com.algorithm.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.algorithm.entity.KeyValue;

/**
 * @interface: KeyValueRep
 * @description: 键值对rep
 * @author Liuzhen
 */
public interface KeyValueRep {
	
	/**
	 * @title:  getListByType
	 * @description: 根据类型获取键值对
	 * @param typeCode
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-8 11:42
	 */
	List<KeyValue> getListByType(@Param("typeCode") String typeCode);
}
