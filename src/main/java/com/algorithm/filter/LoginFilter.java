package com.algorithm.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.algorithm.util.BaseUtil;
import com.algorithm.util.SysProp;

/**
 * @class: LoginFilter
 * @description: 登录过滤器
 * @author Liuzhen
 */
public class LoginFilter implements Filter {
	
	private Set<String> prefixIignores = new HashSet<String>();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext sc = filterConfig.getServletContext();
		String cp = sc.getContextPath();
		String ignoresParam = filterConfig.getInitParameter("ignores");
		String[] ignoreArray = ignoresParam.split(",");
		for (String s : ignoreArray) {
			prefixIignores.add(cp + s);
		}
		setFastdfsPreUrl(sc);
		setLvExpList(sc);
		setKeyValueList(sc);
		setScoreRatio(sc);
	}
	
	@Override
	public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) srequest;
		HttpServletResponse response = (HttpServletResponse) sresponse;
		HttpSession session = request.getSession();
		String uri = request.getRequestURI();
		if (canIgnore(uri)) {
			chain.doFilter(request, response);
			return;
		}
		if (session == null || session.getAttribute(SysProp.LOGIN_USER) == null) {
			response.setCharacterEncoding("UTF-8");
			response.sendRedirect("/login");
			return;
		}
		// 解决ajax请求调用getOutputStream()异常问题
		response.reset();
		// 加入filter链继续向下执行
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {
		prefixIignores = null;
	}
	
	/**
	 * @title:  canIgnore
	 * @description: 筛查filter是否忽略过滤
	 * @param url
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-7 16:09
	 */
	private boolean canIgnore(String url) {
		for (String ignore: prefixIignores) {
			if (url.startsWith(ignore)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @title:  setFastdfsPreUrl
	 * @description: 设置fastdfs图片地址前缀和默认图片地址
	 * @param sc
	 * @author: Liuzhen
	 * @date:   2017-3-7 17:12
	 */
	private void setFastdfsPreUrl(ServletContext sc) {
		if (BaseUtil.getPreUrl(sc) == null) {
			BaseUtil.setPreUrl(sc);
		}
		if (BaseUtil.getDftPreUrl(sc) == null) {
			BaseUtil.setDftPreUrl(sc);
		}
	}
	
	/**
	 * @title:  setLvExpList
	 * @description: 设置用户等级-经验值关系对照关系映射
	 * @param sc
	 * @author: Liuzhen
	 * @date:   2017-3-7 17:14
	 */
	private void setLvExpList(ServletContext sc) {
		if (BaseUtil.getLvExpList(sc) == null) {
			BaseUtil.setLvExpList(sc);
		}
	}
	
	/**
	 * @title:  setKeyValueList
	 * @description: 设置字典键值对
	 * @param sc
	 * @author: Liuzhen
	 * @date:   2017-3-7 17:14
	 */
	private void setKeyValueList(ServletContext sc) {
		if (BaseUtil.getKeyValues(sc, SysProp.KV_SEX) == null) {
			BaseUtil.setKeyValues(sc, SysProp.KV_SEX);
		}
		if (BaseUtil.getKeyValues(sc, SysProp.KV_BLOOD_GROUP) == null) {
			BaseUtil.setKeyValues(sc, SysProp.KV_BLOOD_GROUP);
		}
		if (BaseUtil.getKeyValues(sc, SysProp.KV_STAR_SIGN) == null) {
			BaseUtil.setKeyValues(sc, SysProp.KV_STAR_SIGN);
		}
		if (BaseUtil.getKeyValues(sc, SysProp.KV_SUBJECT_TYPE) == null) {
			BaseUtil.setKeyValues(sc, SysProp.KV_SUBJECT_TYPE);
		}
	}
	
	/**
	 * @title:  setScoreRatio
	 * @description: 设置得分倍率
	 * @param sc
	 * @author: Liuzhen
	 * @date:   2017-3-9 11:57
	 */
	private void setScoreRatio(ServletContext sc) {
		if (BaseUtil.getScoreRatio(sc) == null) {
			BaseUtil.setScoreRatio(sc);
		}
	}
}
