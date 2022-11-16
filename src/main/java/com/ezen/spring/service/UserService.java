package com.ezen.spring.service;

import com.ezen.spring.vo.UserVO;

public interface UserService {
	// 회원 가입
	void joinUser(UserVO userVO);
	
	// 회원 상세 조회
	UserVO getUser(UserVO userVO);
}
