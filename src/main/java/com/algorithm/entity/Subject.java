package com.algorithm.entity;

import java.util.Date;
import java.util.List;

/**
 * @class: Subject
 * @description: 题目entity
 * @author Liuzhen
 */
public class Subject {
	
	private Integer id;				// pk
	private String subTitle;		// 题目标题
	private String subContent;		// 题干内容
	private String exampleInput;	// 样例输入
	private String exampleOutput;	// 样例输出
	private Byte subLevel;			// 题目等级
	private Byte subScore;			// 题目分值
	private Integer relativeScore;	// 题目相对于用户等级,计算收益衰减以及经验倍率后的分值
	private String subType;			// 题目类型[编程题:1, 客观题:2, 主观题:3]
	private String subTypeName;		// 题目类型(名称)
	private Byte subMode;			// 题目模式[废弃:0, 普通:1, 竞赛:2]
	private Integer matchId;		// 竞赛id
	private Integer createId;		// 创建人
	private Date createTime;		// 创建时间
	private List<Answer> ansList;	// 答案列表
	private String userName;		// 用户名
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getExampleInput() {
		return exampleInput;
	}
	public void setExampleInput(String exampleInput) {
		this.exampleInput = exampleInput;
	}
	public String getExampleOutput() {
		return exampleOutput;
	}
	public void setExampleOutput(String exampleOutput) {
		this.exampleOutput = exampleOutput;
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
	public Integer getRelativeScore() {
		return relativeScore;
	}
	public void setRelativeScore(Integer relativeScore) {
		this.relativeScore = relativeScore;
	}
	public String getSubTypeName() {
		return subTypeName;
	}
	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	public Byte getSubMode() {
		return subMode;
	}
	public void setSubMode(Byte subMode) {
		this.subMode = subMode;
	}
	public Integer getMatchId() {
		return matchId;
	}
	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
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
	public List<Answer> getAnsList() {
		return ansList;
	}
	public void setAnsList(List<Answer> ansList) {
		this.ansList = ansList;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
