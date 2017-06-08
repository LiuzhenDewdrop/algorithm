package com.algorithm.entity;
/**
 * @class: LevelExpRelation
 * @description: 用户等级与经验的对应关系entity
 * @author Liuzhen
 */
public class LevelExpRelation {
	
	private Integer id;			// pk
	private Byte userLevel;		// 用户等级
	private String levelName;	// 等级名称
	private Integer expNeeded;	// 该等级升级所需经验值
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Byte getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(Byte userLevel) {
		this.userLevel = userLevel;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public Integer getExpNeeded() {
		return expNeeded;
	}
	public void setExpNeeded(Integer expNeeded) {
		this.expNeeded = expNeeded;
	}
}
