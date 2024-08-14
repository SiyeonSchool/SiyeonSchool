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

import com.kh.contacts.model.vo.Contacts;
import com.kh.contacts.model.vo.ContactsCategory;
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
	
	public ArrayList<ContactsCategory> selectCategoryList(Connection conn) {
		ArrayList<ContactsCategory> list = new ArrayList<ContactsCategory>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCategoryList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ContactsCategory ca = new ContactsCategory();
				ca.setCategoryNo(rset.getInt("CATEGORY_NO"));
				ca.setCategoryName(rset.getString("CATEGORY_NAME"));
				
				list.add(ca);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public ArrayList<Contacts> selectPrivateContactsList(Connection conn, int ownerNo) {
		ArrayList<Contacts> list = new ArrayList<Contacts>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectPrivateContactsList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ownerNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Contacts c = new Contacts();
				c.setContactsNo(rset.getInt("CONTACTS_NO"));
				c.setContactsName(rset.getString("CONTACTS_NAME"));
				c.setUserCount(rset.getInt("COUNT"));
				
				list.add(c);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public ArrayList<Contacts> selectPublicContactsList(Connection conn, int categoryNo) {
		ArrayList<Contacts> list = new ArrayList<Contacts>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectPublicContactsList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Contacts c = new Contacts();
				c.setContactsNo(rset.getInt("CONTACTS_NO"));
				c.setContactsName(rset.getString("CONTACTS_NAME"));
				c.setUserCount(rset.getInt("COUNT"));
				
				list.add(c);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<User> selectAllUsersList(Connection conn) {
		ArrayList<User> list = new ArrayList<User>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAllUsersList");
		
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

	public ArrayList<User> selectUsersList(Connection conn, int contactsNo) {
		ArrayList<User> list = new ArrayList<User>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectUsersList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, contactsNo);
			
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
				u.setRole(rset.getString("ROLE"));
				
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
