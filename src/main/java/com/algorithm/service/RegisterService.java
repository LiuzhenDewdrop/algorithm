package com.algorithm.service;

/**
 * @interface: RegisterService
 * @description: 注册service
 * @author Liuzhen
 */
public interface RegisterService {
	
	/**
	 * @title:  existLoginName
	 * @description: 检查登录名是否存在
	 * @param loginName
	 * @return
	 * @author: Liuzhen
	 * @date:   2017-2-28 9:27
	 */
	boolean existLoginName(String loginName);
	
	/**
	 * @title:  existUserName
	 * @description: 检查用户名是否存在
	 * @param userName
	 * @return
	 * @author: Liuzhen
	 * @date:   2017-2-28 9:28
	 */
	boolean existUserName(String userName);
}
