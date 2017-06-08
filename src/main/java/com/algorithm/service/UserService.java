package com.algorithm.service;

import java.util.List;

import javax.servlet.ServletContext;

import com.algorithm.entity.User;
import com.algorithm.entity.UserHistory;

/**
 * @interface: UserService
 * @description: 用户service
 * @author Liuzhen
 */
public interface UserService {
	
	/**
	 * @title:  getList
	 * @description: 获取用户排行列表
	 * @return
	 * @author: Liuzhen
	 * @date:   2017-3-7 11:19
	 */
	List<User> getList();
	
	/**
	 * @title:  getList4Home
	 * @description: 获取主页展示的用户信息
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-2-23 17:39
	 */
	List<User> getList4Home();
	
	/**
	 * @title:  getUserByLogin
	 * @description: 通过登录获取用户信息
	 * @param loginName
	 * @param loginPsw
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-2-24 17:36
	 */
	User getUserByLogin(String loginName, String loginPsw);
	
	/**
	 * @title:  addUser
	 * @description: 新增用户
	 * @param user
	 * @author: Liuzhen
	 * @date:   2017-2-28 12:25
	 */
	void addUser(User user);
	
	/**
	 * @title:  updateLoginUser
	 * @description: 更新个人用户信息
	 * @param user
	 * @param sc
	 * @author: Liuzhen
	 * @date:   2017-3-1 13:57
	 */
	void updateLoginUser(User user, ServletContext sc);
	
	/**
	 * @title:  updatePsw
	 * @description: 更新密码
	 * @param id
	 * @param psw
	 * @author: Liuzhen
	 * @date:   2017-3-1 15:13
	 */
	void updatePsw(Integer id, String psw);
	
	/**
	 * @title:  getInfoById
	 * @description: 根据主键查找用户
	 * @param id
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-2 9:25
	 */
	User getInfoById(Integer id);
	
	/**
	 * @title:  updateUserExp
	 * @description: 更新用户经验值和等级
	 * @param id
	 * @param userLevel
	 * @param experiencePoint
	 * @author: Liuzhen
	 * @date:   2017-3-6 14:50
	 */
	void updateUserExp(Integer id, String userLevel, Integer experiencePoint);
	
	/**
	 * @title:  getUserHistoryList
	 * @description: 获取用户竞技历史列表
	 * @param userId
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-6 18:36
	 */
	List<UserHistory> getUserHistoryList(Integer userId);
	
	/**
	 * @title:  addUserHistory
	 * @description: 添加一条用户历史信息记录
	 * @param userHistory
	 * @author: Liuzhen
	 * @date:   2017-3-6 18:42
	 */
	void addUserHistory(UserHistory userHistory);
	
	/**
	 * @title:  updateUserRA
	 * @description: 更改用户状态
	 * @param userId
	 * @param index		1、管理员; 2答题者; 3、出题者; 4、判题者; 5、参赛者; 6、启用; 0、禁用。
	 * @author: Liuzhen
	 * @date:   2017-3-9 17:56
	 */
	String updateUserRA(Integer userId, Integer index);
}
