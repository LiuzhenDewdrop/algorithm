package com.algorithm.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.algorithm.entity.UserHistory;

/**
 * @interface: UserHistoryRep
 * @description: 用户竞技历史rep
 * @author Liuzhen
 */
public interface UserHistoryRep {
	
	/**
	 * @title:  getList
	 * @description: 获取列表
	 * @param userId
	 * @return
	 * @author: Liuzhen
	 * @date:   2017-3-6 18:33
	 */
	List<UserHistory> getList(@Param("userId") Integer userId);
	
	/**
	 * @title:  addUserHistory
	 * @description: 添加一个用户历史信息
	 * @param userHistory
	 * @author: Liuzhen
	 * @date:   2017-3-6 18:39
	 */
	void addUserHistory(UserHistory userHistory);
}
