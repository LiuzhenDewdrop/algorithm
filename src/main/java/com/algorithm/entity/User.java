package com.algorithm.entity;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

/**
 * @class: User
 * @description: 用户entity
 * @author Liuzhen
 */
public class User {
	
	private Integer id;					// pk
	private String userName;			// 用户姓名
	private String userLevel;			// 等级
	private String levelName;			// 等级名称
	private Integer experiencePoint;	// 经验值
	private String loginName;			// 登录名
	private String loginPassword;		// 登录密码
	private String sex;					// 性别
	private String bloodGroup;			// 血型
	private String starSign;			// 星座
	private String email;				// 电子邮箱
	private String headPhoto;			// 头像
	private String photoUrl;			// 头像url
	private MultipartFile photoFile;	// 头像文件(上传时使用)
	private String profession;			// 职务
	private String autograph;			// 个性签名
	private String selfIntroduction;	// 个人简介
	private Boolean isActivity;			// 是否禁用[可用:1, 禁用:0]
	private Integer roleId;				// 角色id
	private String roleName;			// 角色名称
	private Date createTime;			// 创建时间
	private Boolean isLogin;			// 是否登录
	private Date loginTime;				// 登录时间
	private String loginIP;				// 登录ip
	private Integer rank;				// 排名
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public Integer getExperiencePoint() {
		return experiencePoint;
	}
	public void setExperiencePoint(Integer experiencePoint) {
		this.experiencePoint = experiencePoint;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getStarSign() {
		return starSign;
	}
	public void setStarSign(String starSign) {
		this.starSign = starSign;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHeadPhoto() {
		return headPhoto;
	}
	public void setHeadPhoto(String headPhoto) {
		this.headPhoto = headPhoto;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public MultipartFile getPhotoFile() {
		return photoFile;
	}
	public void setPhotoFile(MultipartFile photoFile) {
		this.photoFile = photoFile;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getAutograph() {
		return autograph;
	}
	public void setAutograph(String autograph) {
		this.autograph = autograph;
	}
	public String getSelfIntroduction() {
		return selfIntroduction;
	}
	public void setSelfIntroduction(String selfIntroduction) {
		this.selfIntroduction = selfIntroduction;
	}
	
	public Boolean getActivity() {
		return isActivity;
	}
	public void setActivity(Boolean activity) {
		isActivity = activity;
	}
	public Boolean getIsActivity() {
		return isActivity;
	}
	public void setIsActivity(Boolean isActivity) {
		this.isActivity = isActivity;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Boolean getLogin() {
		return isLogin;
	}
	public void setLogin(Boolean login) {
		isLogin = login;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public String getLoginIP() {
		return loginIP;
	}
	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
}
