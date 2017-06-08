package com.algorithm.entity;

import java.util.Date;

/**
 * @class: Judgement
 * @description: 判题entity
 * @author Liuzhen
 */
public class Judgement {
	
	private Integer id;			// pk
	private Integer userId;		// 用户id
	private String userLevel;	// 用户等级
	private Integer subId;		// 题目id
	private Byte subLevel;		// 题目等级
	private Integer ansId;		// 答案id
	private Boolean correct;	// 是否正确
	private Integer score;		// 得分
	private Integer ratio;		// 得分比率*100
	private Long runTime;		// 运行时间(ms)
	private Integer examinerId;	// 阅卷者id
	private Date createTime;	// 阅卷时间
	
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
	public String getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	public Integer getSubId() {
		return subId;
	}
	public void setSubId(Integer subId) {
		this.subId = subId;
	}
	public Byte getSubLevel() {
		return subLevel;
	}
	public void setSubLevel(Byte subLevel) {
		this.subLevel = subLevel;
	}
	public Integer getAnsId() {
		return ansId;
	}
	public void setAnsId(Integer ansId) {
		this.ansId = ansId;
	}
	public Boolean getCorrect() {
		return correct;
	}
	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getRatio() {
		return ratio;
	}
	public void setRatio(Integer ratio) {
		this.ratio = ratio;
	}
	public Long getRunTime() {
		return runTime;
	}
	public void setRunTime(Long runTime) {
		this.runTime = runTime;
	}
	public Integer getExaminerId() {
		return examinerId;
	}
	public void setExaminerId(Integer examinerId) {
		this.examinerId = examinerId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
