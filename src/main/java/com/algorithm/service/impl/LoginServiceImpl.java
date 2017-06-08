package com.algorithm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algorithm.repository.UserRep;
import com.algorithm.service.LoginService;

/**
 * @class: LoginServiceImpl
 * @description: 登录service
 * @author Liuzhen
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private UserRep userRep;
	
	@Override
	public boolean validate(String loginName, String loginPsw) {
		return userRep.countUser(loginName, loginPsw) > 0;
	}
}
