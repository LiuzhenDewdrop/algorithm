package com.algorithm.util;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.algorithm.entity.KeyValue;
import com.algorithm.entity.LevelExpRelation;
import com.algorithm.entity.User;
import com.algorithm.service.ConfigService;
import com.algorithm.service.KeyValueService;
import com.algorithm.service.LevelExpRelationService;

/**
 * @class: BaseUtil
 * @description: base工具
 * @author Liuzhen
 */
public class BaseUtil {
	
	/**
	 * @title:  getSessionUser
	 * @description: 获取登录用户信息
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-2-24 17:13
	 */
	public static User getSessionUser() {
		HttpServletRequest req =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return (User) req.getSession().getAttribute(SysProp.LOGIN_USER);
	}
	
	/**
	 * @title:  setSessionUser
	 * @description: 设置登录用户信息
	 * @param user
	 * @author: Liuzhen
	 * @date:   2017-2-24 17:14
	 */
	public static void setSessionUser(User user) {
		HttpServletRequest req =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		req.getSession().setAttribute(SysProp.LOGIN_USER, user);
		
		
	}
	
	/**
	 * @title:  getIp
	 * @description: 获取用户ip
	 * @param request
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-2-24 17:28
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * @title:  getLvExpList
	 * @description: 获取用户等级-经验值关系对照关系映射
	 * @param sc
	 * @return
	 * @author: Liuzhen
	 * @date:   2017-3-6 14:01
	 */
	public static List<LevelExpRelation> getLvExpList(ServletContext sc) {
		return (List<LevelExpRelation>) sc.getAttribute(SysProp.LV_EXP_RELATION);
	}
	
	/**
	 * @title:  setLvExpList
	 * @description: 设置用户等级-经验值关系对照关系映射
	 * @param sc
	 * @author: Liuzhen
	 * @date:   2017-3-6 14:01
	 */
	public static void setLvExpList(ServletContext sc) {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:/spring/root-context.xml");
		LevelExpRelationService levelExpRelationService = (LevelExpRelationService) classPathXmlApplicationContext.getBean("levelExpRelationService");
		List<LevelExpRelation> list = levelExpRelationService.getList();
		sc.setAttribute(SysProp.LV_EXP_RELATION, list);
	}
	
	/**
	 * @title:  getPreUrl
	 * @description: 获取fastdfs地址前缀
	 * @param sc
	 * @return
	 * @author: Liuzhen
	 * @date:   2017-3-7 17:09
	 */
	public static String getPreUrl(ServletContext sc) {
		return (String) sc.getAttribute(SysProp.PRE_URL_NAME);
	}
	
	/**
	 * @title:  setPreUrl
	 * @description: 设置fastdfs地址前缀
	 * @param sc
	 * @author: Liuzhen
	 * @date:   2017-3-7 16:05
	 */
	public static void setPreUrl(ServletContext sc) {
		sc.setAttribute(SysProp.PRE_URL_NAME, SysProp.PRE_URL);
	}
	
	/**
	 * @title:  getDftPreUrl
	 * @description: 获取默认图片url
	 * @param sc
	 * @return
	 * @author: Liuzhen
	 * @date:   2017-3-7 16:08
	 */
	public static String getDftPreUrl(ServletContext sc) {
		return (String) sc.getAttribute(SysProp.DEFAULT_PRE_URL_NAME);
	}
	
	/**
	 * @title:  setDftPreUrl
	 * @description: 设置默认图片url
	 * @param sc
	 * @author: Liuzhen
	 * @date:   2017-3-7 16:05
	 */
	public static void setDftPreUrl(ServletContext sc) {
		sc.setAttribute(SysProp.DEFAULT_PRE_URL_NAME, SysProp.DEFAULT_PRE_URL);
	}
	
	/**
	 * @title:  getKeyValues
	 * @description: 获取字典键值对
	 * @param sc
	 * @param sysKey
	 * @return
	 * @author: Liuzhen
	 * @date:   2017-3-6 14:01
	 */
	public static List<KeyValue> getKeyValues(ServletContext sc, String sysKey) {
		return (List<KeyValue>) sc.getAttribute(sysKey);
	}
	
	/**
	 * @title:  setKeyValues
	 * @description: 设置字典键值对
	 * @param sc
	 * @param sysKey
	 * @author: Liuzhen
	 * @date:   2017-3-6 14:01
	 */
	public static void setKeyValues(ServletContext sc, String sysKey) {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:/spring/root-context.xml");
		KeyValueService keyValueService = (KeyValueService) classPathXmlApplicationContext.getBean("keyValueService");
		List<KeyValue> list = null;
		switch (sysKey) {
			case SysProp.KV_SEX:
				list = keyValueService.getListByType("sex");
				break;
			case SysProp.KV_BLOOD_GROUP:
				list = keyValueService.getListByType("blood_group");
				break;
			case SysProp.KV_STAR_SIGN:
				list = keyValueService.getListByType("star_sign");
				break;
			case SysProp.KV_SUBJECT_TYPE:
				list = keyValueService.getListByType("subject_type");
		}
		sc.setAttribute(sysKey, list);
	}
	
	/**
	 * @title:  getScoreRatio
	 * @description: 获取得分比率
	 * @param sc
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-9 11:35
	 */
	public static BigDecimal getScoreRatio(ServletContext sc) {
		return (BigDecimal) sc.getAttribute(SysProp.SCORE_RATIO);
	}
	
	/**
	 * @title:  setScoreRatio
	 * @description: 设置得分比率
	 * @param sc
	 * @param ratio
	 * @author: Liuzhen
	 * @date:   2017-3-9 11:35
	 */
	public static void setScoreRatio(ServletContext sc) {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:/spring/root-context.xml");
		ConfigService configService = (ConfigService) classPathXmlApplicationContext.getBean("configService");
		sc.setAttribute(SysProp.SCORE_RATIO, configService.getRatio());
	}
}
