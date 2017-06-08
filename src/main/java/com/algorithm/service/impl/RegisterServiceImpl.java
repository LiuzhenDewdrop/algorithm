package com.algorithm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algorithm.repository.UserRep;
import com.algorithm.service.RegisterService;

/**
 * @class: RegisterServiceImpl
 * @description: 注册service
 * @author Liuzhen
 */
@Service("registerService")
public class RegisterServiceImpl implements RegisterService {
	
	@Autowired
	private UserRep userRep;
	
	@Override
	public boolean existLoginName(String loginName) {
		return userRep.countLoginName(loginName)> 0;
	}
	
	@Override
	public boolean existUserName(String userName) {
		return userRep.countUserName(userName)> 0;
	}
}
