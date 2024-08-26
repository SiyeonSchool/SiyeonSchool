package com.kh.home.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.home.model.vo.Curriculum;
import com.kh.mail.model.vo.Mail;

import static com.kh.common.JDBCTemplate.*;

public class HomeDao {
    private Properties prop = new Properties();

    public HomeDao() {
        try {
            prop.loadFromXML(new FileInputStream(HomeDao.class.getResource("/db/sql/curriculum-mapper.xml").getPath()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ArrayList<Curriculum> selectCurriculum(Connection conn){
        ArrayList<Curriculum> list = new ArrayList<Curriculum>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("selectCurriculum");

        try {
            pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery();

            while(rset.next()) {
                list.add(new Curriculum(rset.getString("cb_name"),
                                        rset.getString("cb_state")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rset);
            close(pstmt);
        }

        return list;
    }

    public int updateCbState(Connection conn, String subject){
        int result = 0;
        PreparedStatement pstmt = null;

        String sql = prop.getProperty("updateCbState");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, subject);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            close(pstmt);
        }

        return result;
    }

    public int getCompletedCount(Connection conn){
        int result = 0;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("getCompletedCount");

        try {
            pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery();

            if(rset.next()){
                result = rset.getInt("count");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            close(pstmt);
        }

        return result;
    }

    public int getTotalCount(Connection conn){
        int result = 0;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("getTotalCount");

        try {
            pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery();

            if(rset.next()){
                result = rset.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            close(pstmt);
        }

        return result;
    }

    public ArrayList<Mail> selectMailList(Connection conn, int userNo){
        ArrayList<Mail> list = new ArrayList<Mail>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectInboxMailList");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userNo);

            rset = pstmt.executeQuery();

            while(rset.next()){
                list.add(new Mail(rset.getString("MAIL_NO"),
								  rset.getString("MAIL_STAR"),
								  rset.getString("IS_READ"),
								  rset.getString("USER_NAME"),
								  rset.getString("USER_ID"),
								  rset.getString("PROFILE_PATH"),
								  rset.getString("MAIL_TITLE"),
								  rset.getString("RECEIVER_TYPE"),
								  rset.getString("SEND_DATE")));
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
}
