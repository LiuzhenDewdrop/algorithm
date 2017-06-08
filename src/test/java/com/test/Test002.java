package com.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.algorithm.util.Calculator;

/**
 * @author Liuzhen
 * @class: Test002
 * @description:
 */
public class Test002 {
	
	public static void main(String[] args) {
		// 存储结果的StringBuffer
//		StringBuffer output = new StringBuffer("");
//		PrintStream ps = new PrintStream(new StringBufferOutputStream(output));
//		// 更改输出位置
//		System.setOut(ps);
//		Scanner sc = new Scanner(System.in);
//		System.out.println(sc.nextLine());
		System.out.println("abcdefghi");
		System.out.println(1);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Long.MAX_VALUE);
		System.out.println((byte)(100 * 0.2));
		System.out.println((byte)(99 * 0.2));
		byte score = 100;
		byte userLevel = 6;
		byte subLevel = 1;
		byte result = (byte) (score * (10 + subLevel - userLevel) * 0.1);
		System.out.println(result);
		System.out.println((byte)(100 * 5 * 0.2));
		System.out.println(Calculator.calcScore((byte) 5, (byte) 1, (byte) 100));
		
		try {
			System.out.println(URLEncoder.encode("大夫撒旦法大赛\r\n", "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String before = "%E6%97%B6%E4%BB%A3%E5%A4%8D%E5%88%86%E7%94%B5%E9%A3%8E%E6%89%87%E5%9C%B0%E6%96%B9%E6%92%92%E6%97%A6%E6%B3%95%E6%92%92%E6%97%A6%E6%B3%95%E6%92%92%E6%97%A6%0D%0A%E5%95%8A%E6%92%92%E6%97%A6%E6%B3%95%E6%92%92%E6%97%A6%E6%B3%95%E6%92%92%E6%97%A6%0D%0A%E4%B8%89%E5%A4%A7%E6%B3%95%E5%B8%88%E6%89%93%E5%8F%91%E5%A3%AB%E5%A4%A7%E5%A4%AB";
		String after = "";
		try {
			after = URLDecoder.decode(before, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(after);
		kjk();
		System.out.println(1);
	}
	
	/**
	 * 锟斤拷
	 */
	private static void kjk() {
		String unicode = "\uFFFD";
		System.out.println("unicode:	" + unicode);
		try {
			String utf8 = URLEncoder.encode(unicode, "UTF-8");
			System.out.println("utf8:	" + utf8);
			System.out.println(URLDecoder.decode(utf8 + utf8, "GBK"));
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public void test() {
		for (int i = 0; i < 3; i++) {
			// do sth.
			System.out.println(1);
		}
	}
	
	public void test1() {
		for (int i = 0; i < 3; i++) {
			dosth();
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void dosth() {
		// do sth.
		System.out.println(1);
	}
}
