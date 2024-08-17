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
import com.kh.contacts.model.vo.ContactsUsersSortInfo;
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
			pstmt.setInt(1, currentUserNo); // 연락처 공개여부용
			pstmt.setInt(2, currentUserNo); // "별"용
			
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
			pstmt.setInt(2, currentUserNo);
			pstmt.setInt(3, contactsNo);
			
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
			pstmt.setInt(2, currentUserNo);
			pstmt.setInt(3, categoryNo);
			
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
	
	// ====================== 정렬 ======================
	
    // sort info을 통해서 xml 중 필요한 entry key 값을 반환하는 메소드
	private String getXmlEntryKey(String StartStr, ContactsUsersSortInfo si) {
		StringBuilder sb = new StringBuilder();
		sb.append(StartStr);
		
		switch(si.getOrderBy()) {
		case "star": sb.append("Star"); break;
		case "userName": sb.append("UserName"); break;
		case "userId": sb.append("UserId"); break;
		case "role": sb.append("Role"); break;
		case "birthday": sb.append("Birthday"); break;
		case "phone": sb.append("Phone"); break;
		}
		
		if(si.isDesc()) {
			sb.append("Desc");
		}else {
			sb.append("Asc");
		}

		return sb.toString();
	}
	
	public ArrayList<User> selectAllUsersListOrderBy(Connection conn, ContactsUsersSortInfo si) {
		ArrayList<User> list = new ArrayList<User>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty(getXmlEntryKey("selectAllUsersListOrderBy", si));
				
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, si.getCurrentUserNo()); // 연락처 공개여부용
			pstmt.setInt(2, si.getCurrentUserNo()); // "별"용
			
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
	
	public ArrayList<User> selectContactsUsersListOrderBy(Connection conn, ContactsUsersSortInfo si) {
		ArrayList<User> list = new ArrayList<User>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty(getXmlEntryKey("selectContactsUsersListOrderBy", si));
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, si.getCurrentUserNo());
			pstmt.setInt(2, si.getCurrentUserNo());
			pstmt.setInt(3, si.getContactsNo());
			
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

	public ArrayList<User> selectCategoryUsersListOrderBy(Connection conn, ContactsUsersSortInfo si) {
		ArrayList<User> list = new ArrayList<User>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty(getXmlEntryKey("selectCategoryUsersListOrderBy", si));
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, si.getCurrentUserNo());
			pstmt.setInt(2, si.getCurrentUserNo());
			pstmt.setInt(3, si.getCategoryNo());
			
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

	public int insertStar(Connection conn, int currentUserNo, int otherUserNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertStar");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, currentUserNo);
			pstmt.setInt(2, otherUserNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteStar(Connection conn, int currentUserNo, int otherUserNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteStar");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, currentUserNo);
			pstmt.setInt(2, otherUserNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Contacts> selectContactsList(Connection conn, int ownerNo) {
		ArrayList<Contacts> list = new ArrayList<Contacts>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectContactsList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ownerNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Contacts(rset.getInt("CONTACTS_NO"),
									  rset.getString("CONTACTS_NAME")));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int insertContactsMember(Connection conn, int contactsNo, ArrayList<Integer> checkedUsersNoList) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertContactsMember");
		
		try {
			for(Integer userNo : checkedUsersNoList) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, contactsNo);
				pstmt.setInt(2, userNo);
				
				result = pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
