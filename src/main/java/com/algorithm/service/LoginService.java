package com.algorithm.service;

/**
 * @interface: LoginService
 * @description: 登录service
 * @author Liuzhen
 */
public interface LoginService {
	
	/**
	 * @title:  validate
	 * @description: 登录验证
	 * @param loginName
	 * @param loginPsw
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-2-24 15:57
	 */
	boolean validate(String loginName, String loginPsw);
	
}
