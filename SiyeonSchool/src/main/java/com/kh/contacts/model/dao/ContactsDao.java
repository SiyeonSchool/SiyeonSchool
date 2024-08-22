package com.kh.contacts.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Properties;

import static com.kh.common.JDBCTemplate.*;

import com.kh.contacts.model.vo.Contacts;
import com.kh.contacts.model.vo.ContactsCategory;
import com.kh.contacts.model.vo.ContactsMember;
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
						          rset.getString("PROFILE_PATH"),
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
						          rset.getString("PROFILE_PATH"),
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
						          rset.getString("PROFILE_PATH"),
						          rset.getString("ROLE"),
						          rset.getString("STAR"),
						          rset.getInt("CONTACTS_NO"),
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
	
	// ====================== 정렬 ======================
	
    // sort info을 통해서 xml 중 필요한 entry key 값을 반환하는 메소드
	private String getXmlEntryKey(String StartStr, ContactsUsersSortInfo si) {
		StringBuilder sb = new StringBuilder();
		sb.append(StartStr);
		
		switch(si.getOrderBy()) {
		case "star": sb.append("Star"); break;
		case "contactsInfo": sb.append("ContactsName"); break;
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
						          rset.getString("PROFILE_PATH"),
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
						          rset.getString("PROFILE_PATH"),
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
						          rset.getString("PROFILE_PATH"),
						          rset.getString("ROLE"),
						          rset.getString("STAR"),
						          rset.getInt("CONTACTS_NO"),
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

	public void insertContactsMember(Connection conn, int contactsNo, ArrayList<Integer> checkedUsersNoList) throws SQLException {
	    String sql = prop.getProperty("insertContactsMember");

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        for (Integer userNo : checkedUsersNoList) {
	            pstmt.setInt(1, contactsNo);
	            pstmt.setInt(2, userNo);
	            
	            try {
	                pstmt.executeUpdate();
	            } catch (SQLIntegrityConstraintViolationException e) {
	                throw new SQLException("중복되는 유저가 있음.", e); // 중복되는 유저가 있는경우 에러를 밖으로 던지면서 for문을 중단하게됨.
	            }
	        }
	    }
	}
	
	public int deleteContactsMember(Connection conn, ArrayList<ContactsMember> contactsMemberList) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteContactsMember");
		
		try {
	        
			for(ContactsMember cm : contactsMemberList) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cm.getContactsNo());
				pstmt.setInt(2, cm.getUserNo());
				
				result = pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertPrivateContacts(Connection conn, String contactsName, int ownerNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertPrivateContacts");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, contactsName);
			pstmt.setInt(2, ownerNo);
			
			result = pstmt.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException e) {
			return -1; // 해당유저의 주소록 중, 중복된 주소록이름이 있는경우 -1을 반환함으로서 명시적으로 중복된다는 걸 알려줌.
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int selectContactNo(Connection conn, String contactsName, int ownerNo) {
		int contactsNo = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectContactsNo");

		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, contactsName);
			pstmt.setInt(2, ownerNo);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				contactsNo = rset.getInt("CONTACTS_NO");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return contactsNo;
	}

	public int insertPublicContacts(Connection conn, int categoryNo, String contactsName) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertPublicContacts");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, contactsName);
			pstmt.setInt(2, categoryNo);
			
			result = pstmt.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException e) {
			return -1; // 관리자의 주소록 중, 중복된 주소록이름이 있는경우 -1을 반환함으로서 명시적으로 중복된다는 걸 알려줌.
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int selectCategoryNoByContactsNo(Connection conn, int contactsNo) {
		int categoryNo = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCategoryNoByContactsNo");

		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, contactsNo);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				categoryNo = rset.getInt("CATEGORY_NO");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return categoryNo;
	}
	
	public int selectCategoryNoByCategoryName(Connection conn, String categoryName) {
		int categoryNo = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCategoryNoByCategoryName");

		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, categoryName);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				categoryNo = rset.getInt("CATEGORY_NO");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return categoryNo;
	}

	public int insertCategory(Connection conn, String newCategoryName) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertCategory");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newCategoryName);
			
			result = pstmt.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException e) {
			return -1; // 중복된 카테고리가 있는경우, -1을 반환함으로서 명시적으로 중복된다는 걸 알려줌.
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteCategory(Connection conn, int categoryNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteCategory");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryNo);
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateCategoryName(Connection conn, int categoryNo, String newCategoryName) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateCategoryName");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newCategoryName);
			pstmt.setInt(2, categoryNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLIntegrityConstraintViolationException e) {
			return -1; // 중복된 카테고리명이 있는경우, -1을 반환함으로서 명시적으로 중복된다는 걸 알려줌.
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateContactsName(Connection conn, int contactsNo, String newContactsName) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateContactsName");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newContactsName);
			pstmt.setInt(2, contactsNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLIntegrityConstraintViolationException e) {
			return -1; // 중복된 카테고리명이 있는경우, -1을 반환함으로서 명시적으로 중복된다는 걸 알려줌.
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteContacts(Connection conn, int contactsNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteContacts");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, contactsNo);
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}
