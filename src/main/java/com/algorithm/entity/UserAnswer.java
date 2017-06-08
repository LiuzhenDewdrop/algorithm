package com.algorithm.entity;

import java.util.Date;

/**
 * @class: UserAnswer
 * @description: 用户答题记录entity
 * @author Liuzhen
 */
public class UserAnswer {
	
	private Integer id;			// pk
	private Integer userId;		// 用户id
	private Integer subId;		// 题目id
	private String answerCode;	// 用户编写答案
	private Boolean status;		// 状态[提交:1, 暂存:0]
	private Date createTime;	// 答题时间
	private String userLevel;	// 用户等级
	private String userName;	// 用户姓名
	private Byte subLevel;		// 题目等级
	private Byte subScore;		// 题目分值
	private String subTitle;	// 题目标题
	private String subContent;	// 题目内容
	
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
	public Integer getSubId() {
		return subId;
	}
	public void setSubId(Integer subId) {
		this.subId = subId;
	}
	public String getAnswerCode() {
		return answerCode;
	}
	public void setAnswerCode(String answerCode) {
		this.answerCode = answerCode;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Byte getSubLevel() {
		return subLevel;
	}
	public void setSubLevel(Byte subLevel) {
		this.subLevel = subLevel;
	}
	public Byte getSubScore() {
		return subScore;
	}
	public void setSubScore(Byte subScore) {
		this.subScore = subScore;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getSubContent() {
		return subContent;
	}
	public void setSubContent(String subContent) {
		this.subContent = subContent;
	}
}
