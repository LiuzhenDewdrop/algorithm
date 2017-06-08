package com.algorithm.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.geronimo.mail.util.StringBufferOutputStream;
import org.apache.tools.ant.filters.StringInputStream;

import com.algorithm.entity.Exam;
import com.algorithm.entity.LevelExpRelation;
import com.minxin.base.common.utils.StringUtil;

/**
 * @class: Calculator
 * @description: 计算器(用于使用AlgorithmUtil)
 * @author Liuzhen
 */
public class Calculator {
	
	private String className = "com.algorithm.util.MinxinIde";
	private String packageName = "package com.algorithm.util;";
	
	/**
	 * @title:  excute
	 * @description: 执行方法
	 * @param exam
	 * @return
	 * @throws IOException 关闭输入流时产生的异常
	 * @author: Liuzhen
	 * @date:   2017-3-2 14:47
	 */
	public Exam excute(Exam exam) throws IOException {
		// 获取输入参数
		InputStream ins = new StringInputStream(encode(exam.getInput()));
		// 更改System.in的获取范围
		System.setIn(ins);
		// 存储结果的StringBuffer
		StringBuffer output = new StringBuffer("");
		PrintStream ps = new PrintStream(new StringBufferOutputStream(output));
		// 更改输出位置
		System.setOut(ps);
		Exam result = new Exam();
		try {
			String loginName = BaseUtil.getSessionUser().getLoginName();
			Long timeStamp = System.currentTimeMillis();
			// 执行程序
			Long runTime = AlgorithmUtil.executeMain(className + loginName + timeStamp,packageName + exam.getCode().replaceFirst("MinxinIde", "MinxinIde" + loginName + timeStamp));
			result.setRunTime(runTime);
		} catch (Exception e) {
//			e.printStackTrace();
			StringBuffer errMsg = new StringBuffer("");
			e.printStackTrace(new PrintStream(new StringBufferOutputStream(errMsg)));
			result.setErrMsg(errMsg.toString());
			return result;
		} finally {
			if (ins != null) {
				ins.close();
			}
			if (ps != null) {
				ps.close();
			}
		}
		result.setOutput(dealOutput(decode(output.toString())));
		return result;
	}
	
	/**
	 * @title:  calcScore
	 * @description: 计算得分
	 * @param userLevel 用户等级
	 * @param subLevel	题目等级
	 * @param score		题目原始得分
	 * @return
	 * @author: Liuzhen
	 * @date:   2017-3-3 17:59
	 */
	public static Integer calcScore(byte userLevel, byte subLevel, byte score) {
		if (userLevel <= subLevel) {
			return (int) score;
		} else if (userLevel - subLevel > 10) {
			return 0;
		} else if (userLevel - subLevel > 5) {
			return (int) (score * 0.2);
		} else {
			return (int) (score * (10 + subLevel - userLevel) * 0.1);
		}
	}
	
	/**
	 * @title:  dealOutput
	 * @description: 去掉因打印而产生的最后一个换行
	 * @param output
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-6 9:27
	 */
	private static String dealOutput(String output) {
		if (StringUtil.isNoneBlank(output) && output.lastIndexOf("\r\n") == output.length() - 2) {
			return output.substring(0, output.length() - 2);
		}
		return output;
	}
	
	/**
	 * @title:  calcUserLevel
	 * @description: 根据现有经验值计算用户等级
	 * @param exp	当前经验
	 * @param sc
	 * @return
	 * @author: Liuzhen
	 * @date:   2017-3-6 14:09
	 */
	public static LevelExpRelation calcUserLevel(Integer exp, ServletContext sc) {
		
		List<LevelExpRelation> lvExpList = BaseUtil.getLvExpList(sc);
		for (LevelExpRelation le : lvExpList) {
			if (exp < le.getExpNeeded()) {
				return le;
			}
		}
		return lvExpList.get(lvExpList.size() - 1);
	}
	
	public static void main(String[] args) {
		String output = "123\r\n";
		output = dealOutput(output);
		System.out.println(output);
	}
	
	/**
	 * @title:  encode
	 * @description: 转码
	 * @param input
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-9 11:15
	 */
	private static String encode(String input) {
		input = input != null ? input : "";
		String result = "";
		try {
			String[] arr = input.replaceAll("\r\n", "\n").split("\n");
			for (String s : arr) {
				result = result + URLEncoder.encode(s, "UTF-8") + "\r\n";
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result.substring(0, result.length() - 2);
	}
	
	private static String decode(String output) {
		try {
			return URLDecoder.decode(output, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
}
