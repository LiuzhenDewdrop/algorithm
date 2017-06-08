package com.algorithm.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.algorithm.entity.User;

/**
 * @interface: UserRep
 * @description: 用户rep
 * @author Liuzhen
 */
public interface UserRep {
	
	/**
	 * @title:  getList
	 * @description: 获取用户列表
	 * @param role	role为1时表示是管理员
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-2-22 12:20
	 */
	List<User> getList(@Param("role") Integer role);
	
	/**
	 * @title:  getList4Home
	 * @description: 获取主页的用户列表
	 * @return
	 * @author: Liuzhen
	 * @date:   2017-2-23 16:12
	 */
	List<User> getList4Home();
	
	/**
	 * @title:  countUser
	 * @description: 通过用户名和密码获取用户数量
	 * @param loginName
	 * @param loginPsw
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-2-24 16:21
	 */
	Integer countUser(@Param("loginName") String loginName, @Param("loginPsw") String loginPsw);
	
	/**
	 * @title:  getUserByLogin
	 * @description: 根据登录获取用户信息
	 * @param loginName
	 * @param loginPsw
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-2-24 17:33
	 */
	User getUserByLogin(@Param("loginName") String loginName, @Param("loginPsw") String loginPsw);
	
	/**
	 * @title:  countLoginName
	 * @description: 检查登录名
	 * @param loginName
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-2-28 9:27
	 */
	Integer countLoginName(@Param("loginName") String loginName);
	
	/**
	 * @title:  countUserName
	 * @description: 检查用户名
	 * @param userName
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-2-28 9:28
	 */
	Integer countUserName(@Param("userName") String userName);
	
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
	 * @author: Liuzhen
	 * @date:   2017-3-1 13:46
	 */
	void updateLoginUser(User user);
	
	/**
	 * @title:  updatePsw
	 * @description: 更新密码
	 * @param id
	 * @param psw
	 * @author: Liuzhen
	 * @date:   2017-3-1 15:13
	 */
	void updatePsw(@Param("id") Integer id, @Param("loginPassword") String psw);
	
	/**
	 * @title:  getInfoById
	 * @description: 根据主键查找用户信息
	 * @param id
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-2 9:28
	 */
	User getInfoById(@Param("id") Integer id);
	
	/**
	 * @title:  updateUserExp
	 * @description: 更新用户经验值和等级
	 * @param id
	 * @param userLevel
	 * @param experiencePoint
	 * @author: Liuzhen
	 * @date:   2017-3-6 14:50
	 */
	void updateUserExp(@Param("id") Integer id, @Param("userLevel") String userLevel, @Param("experiencePoint") Integer experiencePoint);
	
	/**
	 * @title:  updateActivity
	 * @description: 设置用户是否可用
	 * @param id
	 * @param isActivity
	 * @author: Liuzhen
	 * @date:   2017-3-9 17:49
	 */
	void updateActivity(@Param("id") Integer id, @Param("isActivity") Boolean isActivity);
	
	/**
	 * @title:  updateRole
	 * @description: 设置用户角色
	 * @param id
	 * @param roleId
	 * @author: Liuzhen
	 * @date:   2017-3-9 17:51
	 */
	void updateRole(@Param("id") Integer id, @Param("roleId") Integer roleId);
}
