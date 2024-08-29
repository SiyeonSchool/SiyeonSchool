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

    public ArrayList<Attendance> selectAtd(Connection conn, int userNo){
        ArrayList<Attendance> list = new ArrayList<Attendance>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectAtd");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userNo);
            rset = pstmt.executeQuery();

            while(rset.next()){
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

}
