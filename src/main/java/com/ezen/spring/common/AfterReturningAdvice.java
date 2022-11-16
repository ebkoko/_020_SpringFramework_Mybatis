package com.ezen.spring.common;

import org.aspectj.lang.JoinPoint;

import com.ezen.spring.vo.UserVO;

public class AfterReturningAdvice {
	public void afterReturningMethod(JoinPoint jp, Object returnObj) {
		String methodName = jp.getSignature().getName();
		if(returnObj instanceof UserVO) {
			UserVO user = (UserVO)returnObj;
			if(user.getUserId().equals("admin")) {
				System.out.println(user.getUserName() + " 로그인(admin)");
			}
		}
		
		System.out.println("[사후 처리]" + methodName + "() 리턴 값: " + returnObj.toString());
	}
}
