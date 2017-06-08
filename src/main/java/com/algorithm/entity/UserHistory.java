package com.algorithm.entity;

import java.util.Date;

/**
 * @class: UserHistory
 * @description: 用户竞技历史entity
 * @author Liuzhen
 */
public class UserHistory {
	
	private Integer id;			// pk
	private Integer userId;		// 用户id
	private String content;		// 内容
	private Date createTime;	// 创建时间
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
