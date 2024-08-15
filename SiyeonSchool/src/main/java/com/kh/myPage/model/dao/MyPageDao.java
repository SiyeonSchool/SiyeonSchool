package com.kh.myPage.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

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

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            close(pstmt);
        }

        return result;

    }

}
