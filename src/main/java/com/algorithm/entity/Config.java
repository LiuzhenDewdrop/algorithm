package com.algorithm.entity;
/**
 * @class: Config
 * @description: 系统设置entity
 * @author Liuzhen
 */
public class Config {
	
	private Integer id;				// pk
	private String configContent;	// 设置内容描述
	private String configCode;		// 设置内容code
	private String configValue;		// 设置值
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getConfigContent() {
		return configContent;
	}
	public void setConfigContent(String configContent) {
		this.configContent = configContent;
	}
	public String getConfigCode() {
		return configCode;
	}
	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}
	public String getConfigValue() {
		return configValue;
	}
	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
}
