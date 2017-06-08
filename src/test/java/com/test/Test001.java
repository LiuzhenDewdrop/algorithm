package com.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.algorithm.entity.User;
import com.algorithm.entity.UserAnswer;
import com.algorithm.service.SubjectService;
import com.algorithm.service.UserAnswerService;
import com.algorithm.service.UserService;

/**
 * @author Liuzhen
 * @class: Test001
 * @description:
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/root-context.xml" })*/
public class Test001 {
	
	@Test
	public void test001() {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:/spring/root-context.xml");
		UserService userService = (UserService) classPathXmlApplicationContext.getBean("userService");
		List<User> list = userService.getList4Home();
		for (User user : list) {
			System.out.println(user.getId());
			System.out.println(user.getUserName());
		}
		User user = userService.getInfoById(1);
		System.out.println(1);
	}
	
	@Test
	public void test002() {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:/spring/root-context.xml");
		SubjectService subjectService = (SubjectService) classPathXmlApplicationContext.getBean("subjectService");
//		Map<String, BigDecimal> resultMap = subjectService.getCount4Home();
//		List<Subject> list = subjectService.getList(1, BaseUtil.getSessionUser().getUserLevel());
		UserAnswerService userAnswerService = (UserAnswerService) classPathXmlApplicationContext.getBean("userAnswerService");
		UserAnswer userAnswer = userAnswerService.getUserAnswer(1, 1);
		System.out.println(1);
	}
	
	@Test
	public void test003() {
//		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:/spring/root-context.xml");
//		FileDeleteWorker deleteKeeper = (FileDeleteWorker) classPathXmlApplicationContext.getBean("deleteKeeper");
//		String fileId = "group2/M00/00/06/CgoYN1i_cS6AA9izAAAXHL4_3Ys852.jpg";
//		try {
//			deleteKeeper.delete(fileId);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (FastDfsException e) {
//			e.printStackTrace();
//		}
		System.out.println(1);
	}
}
