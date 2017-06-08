package com.algorithm.service;

import java.math.BigDecimal;

import javax.servlet.ServletContext;

/**
 * @interface: ConfigService
 * @description: 
 * @author Liuzhen
 */
public interface ConfigService {
	
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
	 * @param sc
	 * @author: Liuzhen
	 * @date:   2017-3-9 11:44
	 */
	void setRatio(BigDecimal ratio, ServletContext sc);
}
