package com.kh.user.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.kh.common.JDBCTemplate.*;

import com.kh.user.model.vo.Question;
import com.kh.user.model.vo.User;

public class UserDao {

	private Properties prop = new Properties();

	public UserDao() {
		try {
			prop.loadFromXML(new FileInputStream(UserDao.class.getResource("/db/sql/user-mapper.xml").getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public User loginUser(Connection conn, String userId, String userPwd) {
		User u = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("loginUser");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				u = new User(rset.getInt("user_no"), rset.getString("user_id"), rset.getString("user_pwd"),
						rset.getString("user_name"), rset.getString("phone"), rset.getString("phone_public"),
						rset.getString("birthday"), rset.getString("email"), rset.getString("address"),
						rset.getString("enroll_date"), rset.getString("modify_date"), rset.getInt("profile_file_no"),
						rset.getInt("question_no"), rset.getString("question_answer"), rset.getString("user_auth"),
						rset.getString("status"), rset.getString("github_url"), rset.getString("notion_url"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return u;
	}

	public int checkId(Connection conn, String checkId) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("checkId");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, checkId);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				count = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return count;

	}

	public int insertUser(Connection conn, User u) {
		int result = 0;

		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertUser");

		try {
			pstmt = conn.prepareStatement(sql); // 미완성된 쿼리

			pstmt.setString(1, u.getUserId());
			pstmt.setString(2, u.getUserPwd());
			pstmt.setString(3, u.getUserName());
			pstmt.setString(4, u.getPhone());
			pstmt.setString(5, u.getBirthday());
			pstmt.setString(6, u.getEmail());
			pstmt.setString(7, u.getAddress());
			pstmt.setInt(8, u.getQuestionNo());
			pstmt.setString(9, u.getQuestionAnswer());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public ArrayList<Question> selectQuestions(Connection conn) {
	    ArrayList<Question> list = new ArrayList<>();
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    String sql = prop.getProperty("selectQuestions");

	    try {
	        pstmt = conn.prepareStatement(sql);
	        rset = pstmt.executeQuery();

	        while (rset.next()) {
	            Question q = new Question();
	            q.setQuestionNo(rset.getInt("QUESTION_NO"));
	            q.setQuestionContent(rset.getString("QUESTION_CONTENT"));
	            list.add(q);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close(rset);
	        close(pstmt);
	    }

	    return list;
	}
}
