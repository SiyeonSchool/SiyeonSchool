package com.kh.adminPage.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.user.model.vo.User;

import static com.kh.common.JDBCTemplate.*;

public class AdminPageDao {
	
	private Properties prop = new Properties();

    public AdminPageDao() {
        try {
            prop.loadFromXML(new FileInputStream(AdminPageDao.class.getResource("/db/sql/adminPage-mapper.xml").getPath()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ArrayList<User> selectStudentList(Connection conn){
        ArrayList<User> list = new ArrayList<User>();
        PreparedStatement pstmt = null;        
        ResultSet rset = null;
        String sql = prop.getProperty("selectStudentList");

        try {
            pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery();

            while(rset.next()) {
                list.add(new User(rset.getString("user_name"),
                                  rset.getString("phone")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            close(rset);
            close(pstmt);
            close(rset);
        }

        return list;

    }

    public ArrayList<User> selectSignRequest(Connection conn){
        ArrayList<User> list = new ArrayList<User>();
        PreparedStatement pstmt = null;        
        ResultSet rset = null;
        String sql = prop.getProperty("selectSignRequest");

        try {
            pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery();

            while(rset.next()){
                list.add(new User(rset.getInt("user_no"),
                                  rset.getString("user_id"),
                                  rset.getString("user_pwd"),
                                  rset.getString("user_name"),
                                  rset.getString("phone"),
                                  rset.getString("birthday"),
                                  rset.getString("email"),
                                  rset.getString("address"),
                                  rset.getString("enroll_date")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            close(rset);
            close(pstmt);
        }

        return list;
    }

    public int approveUser(Connection conn, int userNo){
        int result = 0;
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("approveUser");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userNo);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            close(pstmt);
        }

        return result;
    }

    public int refusalStudent(Connection conn, int userNo) {
        int result = 0;
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("refusalStudent");
    }

}
