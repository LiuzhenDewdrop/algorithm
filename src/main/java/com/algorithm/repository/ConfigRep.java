package com.algorithm.repository;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

/**
 * @interface: ConfigRep
 * @description: 系统设置rep
 * @author Liuzhen
 */
public interface ConfigRep {
	
	/**
	 * @title:  getRatio
	 * @description: 获取分数比率
	 * @return
	 * @author: Liuzhen
	 * @date:   2017-3-9 11:30
	 */
	BigDecimal getRatio();
	
	/**
	 * @title:  setRatio
	 * @description: 设置得分比率
	 * @param ratio
	 * @author: Liuzhen
	 * @date:   2017-3-9 11:44
	 */
	void setRatio(@Param("ratio") BigDecimal ratio);
}
