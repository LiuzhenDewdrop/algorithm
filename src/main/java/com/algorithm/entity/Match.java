package com.algorithm.entity;

import java.util.Date;

/**
 * @class: Match
 * @description: 竞赛entity
 * @author Liuzhen
 */
public class Match {
	
	private Integer id;				// pk
	private String matchTitle;		// 竞赛标题
	private String matchContent;	// 竞赛描述
	private Byte matchStatus;		// 竞赛状态[废弃:0, 激活:1]
	private Integer createId;		// 创建人id
	private Date createTime;		// 创建时间
	private Integer subCount;		// 题目总数
	private Integer scoreCount;		// 总分
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMatchTitle() {
		return matchTitle;
	}
	public void setMatchTitle(String matchTitle) {
		this.matchTitle = matchTitle;
	}
	public String getMatchContent() {
		return matchContent;
	}
	public void setMatchContent(String matchContent) {
		this.matchContent = matchContent;
	}
	public Byte getMatchStatus() {
		return matchStatus;
	}
	public void setMatchStatus(Byte matchStatus) {
		this.matchStatus = matchStatus;
	}
	public Integer getCreateId() {
		return createId;
	}
	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getSubCount() {
		return subCount;
	}
	public void setSubCount(Integer subCount) {
		this.subCount = subCount;
	}
	public Integer getScoreCount() {
		return scoreCount;
	}
	public void setScoreCount(Integer scoreCount) {
		this.scoreCount = scoreCount;
	}
}
