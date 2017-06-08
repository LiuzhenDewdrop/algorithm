package com.algorithm.util;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * @class: AlgorithmUtil
 * @description: 算法工具
 * @author Liuzhen
 */
public class AlgorithmUtil {
	
	/**
	 * @title:  executeMain
	 * @description: 执行main方法
	 * @param name
	 * @param content
	 * @return
	 * @throws Exception
	 * @author: Liuzhen
	 * @date:   2017-1-23 12:20
	 */
	public static Long executeMain(String name, String content) throws Exception {
		Class<?> claszz = compile(name, content);
		Method method = claszz.getMethod("main", String[].class);
//		System.out.println("method.getName():" + method.getName());
		Long start = System.currentTimeMillis();
		method.invoke(null, new String[][]{new String[]{}});
		return System.currentTimeMillis() - start;
	}
	
	/**
	 * @title:  compile
	 * @description: 编译
	 * @param name
	 * @param content
	 * @return: 编译后的class对象
	 * @throws Exception
	 * @author: Liuzhen
	 * @date:   2017-1-23 12:11
	 */
	private final static Class<?> compile(String name, String content) throws Exception {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		StrSrcJavaObject srcObject = new StrSrcJavaObject(name, content);
		Iterable<? extends JavaFileObject> fileObjects = Arrays.asList(srcObject);
		String flag = "-d";
		String outDir = "";
		File classPath = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
		outDir = classPath.getAbsolutePath() + File.separator;
		Iterable<String> options = Arrays.asList(flag, outDir);
		CompilationTask task = compiler.getTask(null, fileManager, null, options, null, fileObjects);
		if (task.call()) {
			return Class.forName(name);
		}
		return null;
	}
	
	/**
	 * @class: StrSrcJavaObject
	 * @description: 简单java文件对象
	 * @author Liuzhen
	 * @create 2017-1-23 17:22
	 */
	private static class StrSrcJavaObject extends SimpleJavaFileObject {
		private String content;
		public StrSrcJavaObject(String name, String content) {
			super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
			this.content = content;
		}
		public CharSequence getCharContent(boolean ignoreEncodingErrors) {
			return content;
		}
	}
}
