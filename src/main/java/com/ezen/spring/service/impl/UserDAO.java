package com.ezen.spring.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.ezen.spring.common.JDBCUtil;
import com.ezen.spring.vo.UserVO;

@Repository
public class UserDAO {
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private final String USER_JOIN = "INSERT INTO USER VALUES(?, ?, ?, 'USER')";
	private final String GET_USER = "SELECT * FROM USER WHERE ID = ?";
	
	public void joinUser(UserVO userVO) {
		System.out.println("JDBC로 joinUser 기능구현");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_JOIN);
			stmt.setString(1, userVO.getUserId());
			stmt.setString(2, userVO.getUserPassword());
			stmt.setString(3, userVO.getUserName());
			stmt.executeUpdate();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	public UserVO getUser(UserVO userVO) {
		System.out.println("JDBC로 getUser 기능구현");
		UserVO user = new UserVO();
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(GET_USER);
			stmt.setString(1, userVO.getUserId());
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				user.setUserId(rs.getString("ID"));
				user.setUserPassword(rs.getString("PASSWORD"));
				user.setUserName(rs.getString("NAME"));
				user.setUserRole(rs.getString("ROLE"));
			}
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return user;
	}
}
