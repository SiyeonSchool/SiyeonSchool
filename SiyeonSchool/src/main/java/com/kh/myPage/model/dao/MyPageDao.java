package com.kh.myPage.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.myPage.model.vo.Attendance;
import com.kh.user.model.vo.User;

import static com.kh.common.JDBCTemplate.*;

public class MyPageDao {

    private Properties prop = new Properties();

    public MyPageDao() {
        try {
            prop.loadFromXML(new FileInputStream(MyPageDao.class.getResource("/db/sql/myPage-mapper.xml").getPath()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int selectUser(Connection conn, String userId){
        int result = 0;
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("selectUser");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            close(pstmt);
        }

        return result;

    }

    public ArrayList<Attendance> selectAtd(Connection conn, int userNo, String currentMonth){
        ArrayList<Attendance> list = new ArrayList<Attendance>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectAtd");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userNo);
            pstmt.setString(2, currentMonth);
            rset = pstmt.executeQuery();

            while(rset.next()) {
                list.add(new Attendance(rset.getInt("user_no"),
                                        rset.getString("day"),
                                        rset.getString("state_code"),
                                        rset.getString("state_name"),
                                        rset.getInt("use_day_off")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }

        return list;
    }

    public int updateMyInfo(Connection conn, User u){
        int result = 0;
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("updateMyInfo");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, u.getUserName());
            pstmt.setString(2, u.getPhone());
            pstmt.setString(3, u.getAddress());
            pstmt.setString(4, u.getEmail());
            pstmt.setString(5, u.getGithubUrl());
            pstmt.setString(6, u.getNotionUrl());
            pstmt.setInt(7, u.getUserNo());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            close(pstmt);
        }

        return result;
    }

    public User updateSelectUser(Connection conn, int userNo){
        User u = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("loginUser");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);

			rset = pstmt.executeQuery();

            if(rset.next()){
                u = new User(rset.getInt("user_no"),
                             rset.getString("user_id"),
                             rset.getString("user_pwd"),
                             rset.getString("user_name"),
                             rset.getString("phone"),
                             rset.getString("phone_public"),
                             rset.getString("birthday"),
                             rset.getString("email"),
                             rset.getString("address"),
                             rset.getString("enroll_date"),
                             rset.getString("modify_date"),
                             rset.getString("profile_path"),
                             rset.getInt("question_no"),
                             rset.getString("question_answer"),
                             rset.getString("user_auth"),
                             rset.getString("status"),
                             rset.getString("github_url"),
                             rset.getString("notion_url"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rset);
            close(pstmt);
        }

		return u;
	}

}
