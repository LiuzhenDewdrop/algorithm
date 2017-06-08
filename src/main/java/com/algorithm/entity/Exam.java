package com.algorithm.entity;

/**
 * @class: Exam
 * @description: 答题entity(不与数据库关联)
 * @author Liuzhen
 */
public class Exam {
	
	private Integer subId;			// 题目id
	private Integer userAnswerId;	// 用户答题信息id
	private Boolean status;			// 状态(UserAnswer)[提交:1, 暂存:0]
	private String subType;			// 题目类型[编程题:1, 客观题:2, 主观题:3]
	private String input;			// 输入
	private String output;			// 输出
	private String code;			// 代码
	private Long runTime;			// 运行时间(ms)
	private String errMsg;			// 错误信息
	
	public Integer getSubId() {
		return subId;
	}
	public void setSubId(Integer subId) {
		this.subId = subId;
	}
	public Integer getUserAnswerId() {
		return userAnswerId;
	}
	public void setUserAnswerId(Integer userAnswerId) {
		this.userAnswerId = userAnswerId;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getRunTime() {
		return runTime;
	}
	public void setRunTime(Long runTime) {
		this.runTime = runTime;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
}
