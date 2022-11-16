package com.ezen.spring.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.spring.vo.BoardVO;

// DAO: DB에 접근하여 쿼리를 수행하는 클래스
// DAO는 @Repository 어노테이션을 이용하여 객체 자동 생성
// @Controller, @Service, @Repository는 @Component를 상속
// 각각 Controller, ServiceImpl, DAO클래스에 특화된 기능들을 Component보다 더 제공함
@Repository
//JdbcDaoSupport를 상속받아서 JDBC template로 DB연동하는 코드로 변경
public class BoardDAO {	
	// SqlSessionTemplate 받아오기
	@Autowired
	SqlSessionTemplate mybatis;
		   
	// CRUD 기능 구현
	// 글 등록
	public void insertBoard(BoardVO boardVO) {
		System.out.println("JDBC로 insertBoard 기능구현");
		
		mybatis.insert("BoardDAO.insertBoard", boardVO);
	}
	
	// 글 수정
	public void updateBoard(BoardVO boardVO) {
		System.out.println("JDBC로 updateBoard 기능구현");

		mybatis.update("BoardDAO.updateBoard", boardVO);
	}
	
	// 글 삭제
	public void deleteBoard(BoardVO boardVO) {
		System.out.println("JDBC로 deleteBoard 기능구현");

		mybatis.delete("BoardDAO.deleteBoard", boardVO.getBoardNo());
	}
	
	// 글 상세 조회
	public BoardVO getBoard(BoardVO boardVO) {
		System.out.println("JDBC로 getBoard 기능구현");
		Object[] args = {boardVO.getBoardNo()};
		
		// queryForObject의 2번째 매개변수는 무조건 Object[] 형태여야 하므로 boardVO.getBoardNo()을 Object[]로 만들어 삽입한다.
		return mybatis.selectOne("BoardDAO.getBoard", boardVO);
	}
	
	// 글 목록 조회
	public List<BoardVO> getBoardList(BoardVO boardVO) {
		System.out.println("JDBC로 getBoardList 기능구현");
		
		return mybatis.selectList("BoardDAO.getBoardList", boardVO);
	}
}