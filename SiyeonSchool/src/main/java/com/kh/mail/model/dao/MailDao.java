package com.kh.mail.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.kh.common.JDBCTemplate.*;

import com.kh.common.model.vo.PageInfo;
import com.kh.mail.model.vo.Mail;
import com.kh.mail.model.vo.MailReceiver;
import com.kh.mail.model.vo.Mailbox;

public class MailDao {

	private Properties prop = new Properties();
	
	public MailDao() {
		try {
			prop.loadFromXML(new FileInputStream(MailDao.class.getResource("/db/sql/mail-mapper.xml").getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// ===================== 메일 개수 조회 =============================
	
	public ArrayList<Mailbox> selectMailboxCountList(Connection conn, int ownerNo) {
		ArrayList<Mailbox> list = new ArrayList<Mailbox>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMailboxCountList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ownerNo);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new Mailbox(rset.getString("MAILBOX_NO"),
									 rset.getString("MAILBOX_NAME"),
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

	public int selectUnreadMailCount(Connection conn, int ownerNo) {
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectUnreadMailCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ownerNo);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				listCount = rset.getInt("count");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	

	public int selectImportantMailCount(Connection conn, int ownerNo) {
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectImportantMailCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ownerNo);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				listCount = rset.getInt("count");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	
	// ===================== 메일 목록 조회 =============================
	
	public ArrayList<Mail> selectAllMailList(Connection conn, int ownerNo, PageInfo pi) {
		ArrayList<Mail> list = new ArrayList<Mail>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAllMailList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getcPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, ownerNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Mail(rset.getString("MAIL_NO"),
						          rset.getString("MAILBOX_NAME"),
								  rset.getString("MAIL_STAR"),
								  rset.getString("USER_NAME"),
								  rset.getString("USER_ID"),
								  rset.getString("PROFILE_PATH"),
								  rset.getString("MAIL_TITLE"),
								  rset.getString("SEND_DATE")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public ArrayList<Mail> selectInboxMailList(Connection conn, int ownerNo, PageInfo pi) {
		ArrayList<Mail> list = new ArrayList<Mail>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectInboxMailList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getcPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, ownerNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
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
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public ArrayList<Mail> selectSentMailList(Connection conn, int ownerNo, PageInfo pi) {
		ArrayList<Mail> list = new ArrayList<Mail>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectSentMailList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getcPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, ownerNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Mail(rset.getString("MAIL_NO"),
								  rset.getString("MAIL_STAR"),
								  rset.getString("USER_NAME"),
								  rset.getString("USER_ID"),
								  rset.getString("PROFILE_PATH"),
								  rset.getString("MAIL_TITLE"),
								  rset.getString("SEND_DATE")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Mail> selectTempMailList(Connection conn, int ownerNo, PageInfo pi) {
		ArrayList<Mail> list = new ArrayList<Mail>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectTempMailList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getcPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, ownerNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Mail(rset.getString("MAIL_NO"),
								  rset.getString("MAIL_STAR"),
								  rset.getString("USER_NAME"),
								  rset.getString("USER_ID"),
								  rset.getString("PROFILE_PATH"),
								  rset.getString("MAIL_TITLE"),
								  rset.getString("SEND_DATE")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Mail> selectToMyselfMailList(Connection conn, int ownerNo, PageInfo pi) {
		ArrayList<Mail> list = new ArrayList<Mail>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectToMyselfMailList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getcPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, ownerNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Mail(rset.getString("MAIL_NO"),
								  rset.getString("MAIL_STAR"),
								  rset.getString("USER_NAME"),
								  rset.getString("USER_ID"),
								  rset.getString("PROFILE_PATH"),
								  rset.getString("MAIL_TITLE"),
								  rset.getString("SEND_DATE")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Mail> selectBinMailList(Connection conn, int ownerNo, PageInfo pi) {
		ArrayList<Mail> list = new ArrayList<Mail>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectBinMailList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getcPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, ownerNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Mail(rset.getString("MAIL_NO"),
								  rset.getString("MAIL_STAR"),
								  rset.getString("USER_NAME"),
								  rset.getString("USER_ID"),
								  rset.getString("PROFILE_PATH"),
								  rset.getString("MAIL_TITLE"),
								  rset.getString("SEND_DATE")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Mail> selectUnreadMailList(Connection conn, int ownerNo, PageInfo pi) {
		ArrayList<Mail> list = new ArrayList<Mail>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectUnreadMailList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getcPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, ownerNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
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
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Mail> selectImportantMailList(Connection conn, int ownerNo, PageInfo pi) {
		ArrayList<Mail> list = new ArrayList<Mail>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectImportantMailList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getcPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, ownerNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Mail(rset.getString("MAIL_NO"),
						          rset.getString("MAILBOX_NAME"),
								  rset.getString("MAIL_STAR"),
								  rset.getString("USER_NAME"),
								  rset.getString("USER_ID"),
								  rset.getString("PROFILE_PATH"),
								  rset.getString("MAIL_TITLE"),
								  rset.getString("SEND_DATE")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	// ===================== 메일 상세 조회 =============================
	
	public Mail selectMail(Connection conn, int ownerNo, String mailNo) {
		Mail m = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMail");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, ownerNo);
			pstmt.setString(2, mailNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Mail();
				m.setMailNo(rset.getString("MAIL_NO"));
				m.setMailStar(rset.getString("MAIL_STAR"));
				m.setMailTitle(rset.getString("MAIL_TITLE"));
				m.setUserName(rset.getString("USER_NAME"));
				m.setUserId(rset.getString("USER_ID"));
				m.setProfilePath(rset.getString("PROFILE_PATH"));
				m.setSendDate(rset.getString("SEND_DATE"));
				m.setMailContent(rset.getString("MAIL_CONTENT"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return m;
	}

	public ArrayList<MailReceiver> selectMailReceiverList(Connection conn, String mailNo) {
		ArrayList<MailReceiver> list = new ArrayList<MailReceiver>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMailReceiverList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mailNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new MailReceiver(rset.getString("MAIL_NO"),
						  rset.getInt("RECEIVER_NO"),
						  rset.getString("USER_NAME"),
						  rset.getString("USER_ID"),
						  rset.getString("RECEIVER_TYPE"),
						  rset.getString("READ_TIME")
						 ));
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
