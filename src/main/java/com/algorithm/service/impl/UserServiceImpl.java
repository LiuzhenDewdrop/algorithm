package com.algorithm.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ProtoCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.fast.exception.FastDfsException;
import com.jy.fast.task.FileDeleteWorker;
import com.jy.fast.task.SynSingleFileUploadWorker;
import com.algorithm.entity.User;
import com.algorithm.entity.UserHistory;
import com.algorithm.repository.UserHistoryRep;
import com.algorithm.repository.UserRep;
import com.algorithm.service.UserService;
import com.algorithm.util.BaseUtil;
import com.algorithm.util.MD5;

/**
 * @class: UserServiceImpl
 * @description: 用户service
 * @author Liuzhen
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRep userRep;
	
	@Autowired
	private UserHistoryRep userHistoryRep;
	
	@Autowired
	private SynSingleFileUploadWorker synSingleFileUploadWorker;
	
	@Autowired
	private FileDeleteWorker deleteKeeper;
	
	@Override
	public List<User> getList() {
		return userRep.getList(BaseUtil.getSessionUser().getRoleId());
	}
	
	@Override
	public List<User> getList4Home() {
		return userRep.getList4Home();
	}
	
	@Override
	public User getUserByLogin(String loginName, String loginPsw) {
		return userRep.getUserByLogin(loginName, loginPsw);
	}
	
	@Override
	public void addUser(User user) {
		user.setLoginPassword(MD5.encrypt(user.getLoginPassword()));
		user.setUserLevel("1");
		user.setExperiencePoint(0);
		user.setRoleId(2);
		user.setIsActivity(true);
		user.setCreateTime(new Date());
		userRep.addUser(user);
		// 注册用户之后添加一条历史记录
		UserHistory userHistory = new UserHistory();
		userHistory.setUserId(user.getId());
		userHistory.setContent("成功注册成为一名英勇的算法战士!");
		userHistory.setCreateTime(new Date());
		userHistoryRep.addUserHistory(userHistory);
	}
	
	@Override
	public void updateLoginUser(User user, ServletContext sc) {
		User loginUser = BaseUtil.getSessionUser();
		user.setId(loginUser.getId());
		// 是否更新了照片
		boolean isUpdatePhoto = user.getPhotoFile() != null && user.getPhotoFile().getSize() >0;
		if (isUpdatePhoto) {
			NameValuePair[] metaList = new NameValuePair[1];
			metaList[0] = new NameValuePair("fileName", user.getPhotoFile().getOriginalFilename());
			String fileExtName = null;
			int nPos = user.getPhotoFile().getOriginalFilename().lastIndexOf('.');
			if (nPos > 0 && user.getPhotoFile().getOriginalFilename().length() - nPos <= ProtoCommon.FDFS_FILE_EXT_NAME_MAX_LEN + 1) {
				fileExtName = user.getPhotoFile().getOriginalFilename().substring(nPos + 1);
			}
			// 通过fastdfs上传图片
			String photoUrl = "";
			try {
				photoUrl = synSingleFileUploadWorker.upload(user.getPhotoFile().getBytes(), metaList, fileExtName);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (FastDfsException e) {
				e.printStackTrace();
			}
			user.setPhotoUrl(photoUrl);
		}
		// 更新登录用户信息
		userRep.updateLoginUser(user);
		// 更新数据库后更新session中的用户信息
		loginUser.setUserName(user.getUserName());
		loginUser.setSex(user.getSex());
		loginUser.setBloodGroup(user.getBloodGroup());
		loginUser.setStarSign(user.getStarSign());
		loginUser.setEmail(user.getEmail());
		loginUser.setProfession(user.getProfession());
		loginUser.setAutograph(user.getAutograph());
		loginUser.setSelfIntroduction(user.getSelfIntroduction());
		loginUser.setHeadPhoto(user.getHeadPhoto());
		String delPhotoUrl = loginUser.getPhotoUrl();
		loginUser.setPhotoUrl(user.getPhotoUrl());
		// 更新信息成功后删除原来的图片
		if (isUpdatePhoto) {
			try {
				deleteKeeper.delete(delPhotoUrl);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (FastDfsException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void updatePsw(Integer id, String psw) {
		userRep.updatePsw(id, psw);
	}
	
	@Override
	public User getInfoById(Integer id) {
		return userRep.getInfoById(id);
	}
	
	@Override
	public void updateUserExp(Integer id, String userLevel, Integer experiencePoint) {
		userRep.updateUserExp(id, userLevel, experiencePoint);
	}
	
	@Override
	public List<UserHistory> getUserHistoryList(Integer userId) {
		return userHistoryRep.getList(userId);
	}
	
	@Override
	public void addUserHistory(UserHistory userHistory) {
		userHistoryRep.addUserHistory(userHistory);
	}
	
	@Override
	public String updateUserRA(Integer userId, Integer index) {
		switch (index) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
				userRep.updateRole(userId, index);
				break;
			case 6:
			case 0:
				userRep.updateActivity(userId, index > 0);
				break;
			default:
				return "输入数字错误";
		}
		return "成功";
	}
}
