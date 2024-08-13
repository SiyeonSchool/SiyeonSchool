package com.kh.contacts.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.kh.common.JDBCTemplate.*;
import com.kh.user.model.vo.User;

public class ContactsDao {
	
	private Properties prop = new Properties();
	
	public ContactsDao() {
		String filePath = User.class.getResource("/db/sql/contacts-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<User> selectUserList(Connection conn) {
		ArrayList<User> list = new ArrayList<User>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAllUserList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				User u = new User();
				u.setUserNo(rset.getInt("USER_NO"));
				u.setUserId(rset.getString("USER_ID"));
				u.setUserName(rset.getString("USER_NAME"));
				u.setPhone(rset.getString("PHONE"));
				u.setPhonePublic(rset.getString("PHONE_PUBLIC"));
				u.setBirthday(rset.getString("BIRTHDAY"));
				u.setProfileFileNo(rset.getInt("PROFILE_FILE_NO"));
				u.setUserAuth(rset.getString("USER_AUTH"));
				
				list.add(u);
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
