package com.algorithm.util;

import java.security.MessageDigest;

/**
 * @class: MD5
 * @description: md5加密
 * @author Liuzhen
 */
public class MD5 {
	
	private static MessageDigest md;
	
	public MD5() {
		init();
	}
	
	private static void init() {
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	/**
	 * @title:  encrypt
	 * @description: 加密
	 * @param inStr
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-2-28 12:29
	 */
	public static String encrypt(String inStr) {
		init();
		byte[] bytes1 = md.digest(inStr.getBytes());
		StringBuffer sb1 = new StringBuffer();
		for (byte b : bytes1) {
			int temp = ((int) b) & 0xff;
			if (temp < 16) {
				sb1.append("0");
			}
			sb1.append(Integer.toHexString(temp));
		}
		byte[] bytes2 = md.digest(sb1.append(SysProp.SALT1).toString().getBytes());
		StringBuffer sb2 = new StringBuffer();
		for (int i = bytes2.length - 1; i >= 0; i--) {
			int temp = ((int) bytes2[i]) & 0xff;
			if (temp < 16) {
				sb2.append("0");
			}
			sb2.append(Integer.toHexString(temp));
		}
		byte[] bytes3 = md.digest(sb2.append(SysProp.SALT2).toString().getBytes());
		StringBuffer result = new StringBuffer();
		for (byte b : bytes3) {
			int temp = ((int) b) & 0xff;
			if (temp < 16) {
				result.append("0");
			}
			result.append(Integer.toHexString(temp));
		}
		return result.toString();
	}
	
	public static void main(String[] args) {
		byte[] bytes = new MD5().md.digest("123".getBytes());
		System.out.println(bytes.length);
		String result = "";
		for (byte b : bytes) {
			int temp = ((int) b) & 0xff;
			if (temp < 16) {
				result += "0";
			}
			result += Integer.toHexString(temp);
		}
		System.out.println(result);
		System.out.println(result.length());
		System.out.println(new MD5().encrypt("123456"));
	}
}
