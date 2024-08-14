package com.kh.user.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.kh.common.JDBCTemplate.*;
import com.kh.user.model.vo.User;

public class UserDao {

    private Properties prop = new Properties();

    public UserDao(){
        try {
            prop.loadFromXML(new FileInputStream(UserDao.class.getResource("/db/sql/user-mapper.xml").getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User loginUser(Connection conn,String userId, String userPwd){
        User u = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("loginUser");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, userPwd);

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
                             rset.getInt("profile_file_no"),
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
