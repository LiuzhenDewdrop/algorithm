package com.algorithm.entity;

/**
 * @class: Answer
 * @description: 答案entity
 * @author Liuzhen
 */
public class Answer {
	
	private Integer id;			// pk
	private Integer subId;		// 题目id
	private String ansInput;	// 输入用例
	private String ansOutput;	// 输出(答案)
	private Float proportion;	// 占比
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSubId() {
		return subId;
	}
	public void setSubId(Integer subId) {
		this.subId = subId;
	}
	
	public String getAnsInput() {
		return ansInput;
	}
	public void setAnsInput(String ansInput) {
		this.ansInput = ansInput;
	}
	public String getAnsOutput() {
		return ansOutput;
	}
	public void setAnsOutput(String ansOutput) {
		this.ansOutput = ansOutput;
	}
	public Float getProportion() {
		return proportion;
	}
	public void setProportion(Float proportion) {
		this.proportion = proportion;
	}
}
