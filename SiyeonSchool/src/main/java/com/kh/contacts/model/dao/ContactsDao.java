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
				list.add(new ContactsCategory(rset.getInt("CATEGORY_NO"),
					      					  rset.getString("CATEGORY_NAME")));
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
				list.add(new Contacts(rset.getInt("CONTACTS_NO"),
									  rset.getString("CONTACTS_NAME"),
									  rset.getInt("COUNT")));
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
				list.add(new Contacts(rset.getInt("CONTACTS_NO"),
									  rset.getString("CONTACTS_NAME"),
									  rset.getInt("COUNT")));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<User> selectAllUsersList(Connection conn, int currentUserNo) {
		ArrayList<User> list = new ArrayList<User>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAllUsersList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, currentUserNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new User(rset.getInt("USER_NO"),
						          rset.getString("USER_ID"),
						          rset.getString("USER_NAME"),
						          rset.getString("PHONE"),
						          rset.getString("BIRTHDAY"),
						          rset.getInt("PROFILE_FILE_NO"),
						          rset.getString("ROLE"),
						          rset.getString("STAR")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<User> selectContactsUsersList(Connection conn, int currentUserNo, int contactsNo) {
		ArrayList<User> list = new ArrayList<User>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectContactsUsersList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, currentUserNo);
			pstmt.setInt(2, contactsNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new User(rset.getInt("USER_NO"),
						          rset.getString("USER_ID"),
						          rset.getString("USER_NAME"),
						          rset.getString("PHONE"),
						          rset.getString("BIRTHDAY"),
						          rset.getInt("PROFILE_FILE_NO"),
						          rset.getString("ROLE"),
						          rset.getString("STAR")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public ArrayList<User> selectCategoryUsersList(Connection conn, int currentUserNo, int categoryNo) {
		ArrayList<User> list = new ArrayList<User>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCategoryUsersList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, currentUserNo);
			pstmt.setInt(2, categoryNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new User(rset.getInt("USER_NO"),
						          rset.getString("USER_ID"),
						          rset.getString("USER_NAME"),
						          rset.getString("PHONE"),
						          rset.getString("BIRTHDAY"),
						          rset.getInt("PROFILE_FILE_NO"),
						          rset.getString("ROLE"),
						          rset.getString("STAR")));
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
