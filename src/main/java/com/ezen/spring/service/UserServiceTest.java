package com.ezen.spring.service;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.ezen.spring.vo.UserVO;

public class UserServiceTest {

	public static void main(String[] args) {
		// 1. 스프링 컨테이너 구동
		AbstractApplicationContext applicationContext = new GenericXmlApplicationContext("root-context.xml");
		
		
		// 2. UserServiceImpl 객체를 Lookup
		UserService userService = (UserService)applicationContext.getBean("userService");
		
		// 3. 회원가입 테스트
		/*
		 * UserVO userVO = new UserVO(); userVO.setUserId("ezen02");
		 * userVO.setUserPassword("1234"); userVO.setUserName("고길동");
		 * 
		 * userService.joinUser(userVO);
		 */
		
		// 4. 회원 상세 조회 테스트
		// ID 세팅하기 위한 UserVO 객체
		UserVO infoUser = new UserVO();
		infoUser.setUserId("admin");
		
		UserVO user = userService.getUser(infoUser);
		System.out.println(user.toString());
		
		// 5. 스프링 컨테이너 종료
		applicationContext.close();
	}

}
