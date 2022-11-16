package com.ezen.spring.service.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.ezen.spring.vo.BoardVO;

// DAO: DB에 접근하여 쿼리를 수행하는 클래스
// DAO는 @Repository 어노테이션을 이용하여 객체 자동 생성
// @Controller, @Service, @Repository는 @Component를 상속
// 각각 Controller, ServiceImpl, DAO클래스에 특화된 기능들을 Component보다 더 제공함
//@Repository
//JdbcDaoSupport를 상속받아서 JDBC template로 DB연동하는 코드로 변경
public class BoardDAOJdbcDaoSupport extends JdbcDaoSupport {	
	// SQL 쿼리(상수로 선언)
	private final String BOARD_INSERT = "INSERT INTO BOARD VALUES((SELECT IFNULL(MAX(A.BOARD_NO), 0) + 1 FROM BOARD A), ?, ?, ?, now(), 0)";
	private final String BOARD_UPDATE = "UPDATE BOARD SET BOARD_TITLE = ?, BOARD_CONTENT = ?, BOARD_WRITER = ? WHERE BOARD_NO = ?";
	private final String BOARD_DELETE = "DELETE FROM BOARD WHERE BOARD_NO = ?";
	private final String BOARD_GET = "SELECT * FROM BOARD WHERE BOARD_NO = ?";
	private final String BOARD_LIST = "SELECT * FROM BOARD ORDER BY BOARD_NO DESC";
	
	// JDBC template 의존성 주입
	@Autowired // 매개변수도 같은 모양의 객체를 찾아 자동으로 의존성 주입
	public void setSuperDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}
   
	// CRUD 기능 구현
	// 글 등록
	public void insertBoard(BoardVO boardVO) {
		System.out.println("JDBC로 insertBoard 기능구현");
		
		getJdbcTemplate().update(BOARD_INSERT, boardVO.getBoardTitle(), boardVO.getBoardContent(), boardVO.getBoardWriter());
	}
	
	// 글 수정
	public void updateBoard(BoardVO boardVO) {
		System.out.println("JDBC로 updateBoard 기능구현");

		getJdbcTemplate().update(BOARD_UPDATE, boardVO.getBoardTitle(), boardVO.getBoardContent(), boardVO.getBoardWriter(), boardVO.getBoardNo());
	}
	
	// 글 삭제
	public void deleteBoard(BoardVO boardVO) {
		System.out.println("JDBC로 deleteBoard 기능구현");

		getJdbcTemplate().update(BOARD_DELETE, boardVO.getBoardNo());
	}
	
	// 글 상세 조회
	public BoardVO getBoard(BoardVO boardVO) {
		System.out.println("JDBC로 getBoard 기능구현");
		Object[] args = {boardVO.getBoardNo()};
		
		// queryForObject의 2번째 매개변수는 무조건 Object[] 형태여야 하므로 boardVO.getBoardNo()을 Object[]로 만들어 삽입한다.
		return getJdbcTemplate().queryForObject(BOARD_GET, args, new BoardRowMapper());
	}
	
	// 글 목록 조회
	public List<BoardVO> getBoardList() {
		System.out.println("JDBC로 getBoardList 기능구현");
		
		return getJdbcTemplate().query(BOARD_LIST, new BoardRowMapper());
	}
}