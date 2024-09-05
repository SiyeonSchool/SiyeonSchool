package com.kh.mail.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import static com.kh.common.JDBCTemplate.*;

import com.kh.common.model.vo.Attachment;
import com.kh.common.model.vo.PageInfo;
import com.kh.mail.model.vo.Mail;
import com.kh.mail.model.vo.MailReceiver;
import com.kh.mail.model.vo.MailWriteSearchResult;
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
	
	public ArrayList<Mailbox> selectPrivateMailboxCountList(Connection conn, int ownerNo) {
		ArrayList<Mailbox> list = new ArrayList<Mailbox>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectPrivateMailboxCountList");
		
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
				Mail m = new Mail();
				m.setMailNo(rset.getString("MAIL_NO"));
				m.setMailboxName(rset.getString("MAILBOX_NAME"));
				m.setMailStar(rset.getString("MAIL_STAR"));
				m.setIsRead(rset.getString("IS_READ"));
				m.setAttachmentCount(rset.getInt("ATT_COUNT"));
				m.setUserName(rset.getString("USER_NAME"));
				m.setUserId(rset.getString("USER_ID"));
				m.setProfilePath(rset.getString("PROFILE_PATH"));
				m.setMailTitle(rset.getString("MAIL_TITLE"));
				m.setSendDate(rset.getString("SEND_DATE"));
				list.add(m);
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
								  rset.getInt("ATT_COUNT"),
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
								  rset.getInt("ATT_COUNT"),
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
								  rset.getInt("ATT_COUNT"),
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
				Mail m = new Mail();
				m.setMailNo(rset.getString("MAIL_NO"));
				m.setMailStar(rset.getString("MAIL_STAR"));
				m.setIsRead(rset.getString("IS_READ"));
				m.setAttachmentCount(rset.getInt("ATT_COUNT"));
				m.setUserName(rset.getString("USER_NAME"));
				m.setUserId(rset.getString("USER_ID"));
				m.setProfilePath(rset.getString("PROFILE_PATH"));
				m.setMailTitle(rset.getString("MAIL_TITLE"));
				m.setSendDate(rset.getString("SEND_DATE"));
				list.add(m);
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
								  rset.getInt("ATT_COUNT"),
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
				Mail m = new Mail();
				m.setMailNo(rset.getString("MAIL_NO"));
				m.setMailStar(rset.getString("MAIL_STAR"));
				m.setIsRead(rset.getString("IS_READ"));
				m.setAttachmentCount(rset.getInt("ATT_COUNT"));
				m.setUserName(rset.getString("USER_NAME"));
				m.setUserId(rset.getString("USER_ID"));
				m.setProfilePath(rset.getString("PROFILE_PATH"));
				m.setMailTitle(rset.getString("MAIL_TITLE"));
				m.setReceiverType(rset.getString("RECEIVER_TYPE"));
				m.setSendDate(rset.getString("SEND_DATE"));
				list.add(m);
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
				Mail m = new Mail();
				m.setMailNo(rset.getString("MAIL_NO"));
				m.setMailboxName(rset.getString("MAILBOX_NAME"));
				m.setMailStar(rset.getString("MAIL_STAR"));
				m.setIsRead(rset.getString("IS_READ"));
				m.setAttachmentCount(rset.getInt("ATT_COUNT"));
				m.setUserName(rset.getString("USER_NAME"));
				m.setUserId(rset.getString("USER_ID"));
				m.setProfilePath(rset.getString("PROFILE_PATH"));
				m.setMailTitle(rset.getString("MAIL_TITLE"));
				m.setSendDate(rset.getString("SEND_DATE"));
				list.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}


	public ArrayList<Mail> selectPrivateMailboxMailList(Connection conn, String mailboxNo, PageInfo pi) {
		ArrayList<Mail> list = new ArrayList<Mail>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectPrivateMailboxMailList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getcPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, mailboxNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Mail m = new Mail();
				m.setMailNo(rset.getString("MAIL_NO"));
				m.setMailStar(rset.getString("MAIL_STAR"));
				m.setIsRead(rset.getString("IS_READ"));
				m.setAttachmentCount(rset.getInt("ATT_COUNT"));
				m.setUserName(rset.getString("USER_NAME"));
				m.setUserId(rset.getString("USER_ID"));
				m.setProfilePath(rset.getString("PROFILE_PATH"));
				m.setMailTitle(rset.getString("MAIL_TITLE"));
				m.setSendDate(rset.getString("SEND_DATE"));
				list.add(m);
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

	public HashMap<String, Integer> selectMailReceiverTypeCount(Connection conn, String mailNo) {
		HashMap<String, Integer> map = new HashMap<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMailReceiverTypeCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mailNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				map.put(rset.getString("RECEIVER_TYPE"),
						rset.getInt("COUNT"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return map;
	}

	public ArrayList<Attachment> selectAttachmentList(Connection conn, String mailNo) {
		ArrayList<Attachment> list = new ArrayList<Attachment>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAttachmentList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mailNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Attachment(rset.getString("FILE_NO"),
										rset.getString("ORIGIN_NAME"),
										rset.getString("CHANGE_NAME"),
										rset.getString("FILE_PATH")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	// ===================== 별 수정 =============================
	
	public int updateStar(Connection conn, String starYN, int ownerNo, String mailNo) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateStar");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, starYN);
			pstmt.setInt(2, ownerNo);
			pstmt.setString(3, mailNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// ===================== 읽음 수정 =============================
	
	public String selectIsRead(Connection conn, int ownerNo, String mailNo) {
		String isRead = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectIsRead");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ownerNo);
			pstmt.setString(2, mailNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				isRead = rset.getString("IS_READ");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return isRead;
	}
	
	
	
	public int updateReadToNull(Connection conn, int ownerNo, String mailNo) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateReadToNull");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ownerNo);
			pstmt.setString(2, mailNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateReadToSysdate(Connection conn, int ownerNo, String mailNo) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateReadToSysdate");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ownerNo);
			pstmt.setString(2, mailNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// ===================== 메일 보내기 =============================
	
	public int insertMail(Connection conn, Mail m) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMail");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m.getUserNo());
			pstmt.setString(2, m.getMailTitle());
			pstmt.setString(3, m.getMailContent());
			pstmt.setString(4, m.getIsSent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int insertAttachment(Connection conn, Attachment at) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertMailReceiver(Connection conn, MailReceiver mr) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMailReceiver");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mr.getReceiverNo());
			pstmt.setString(2, mr.getReceiverType());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public String selectSentMailboxNo(Connection conn, int ownerNo) {
		String mailboxNo = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectSentMailboxNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ownerNo);

			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				mailboxNo = rset.getString("MAILBOX_NO");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return mailboxNo;
	}


	public String selectInboxNo(Connection conn, int ownerNo) {
		String mailboxNo = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectInboxNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ownerNo);

			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				mailboxNo = rset.getString("MAILBOX_NO");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return mailboxNo;
	}


	public String selectMyselfMailboxNo(Connection conn, int loginUserNo) {
		String mailboxNo = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMyselfMailboxNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, loginUserNo);

			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				mailboxNo = rset.getString("MAILBOX_NO");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return mailboxNo;
	}
	
	public String selectTempMailboxNo(Connection conn, int loginUserNo) {
		String mailboxNo = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectTempMailboxNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, loginUserNo);

			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				mailboxNo = rset.getString("MAILBOX_NO");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return mailboxNo;
	}
	
	public int insertMailOwner(Connection conn, int ownerNo, String mailboxNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMailOwner");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ownerNo);
			pstmt.setString(2, mailboxNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
	// ===================== 수신인 검색관련 =============================
	
	public MailWriteSearchResult selectTeacher(Connection conn) {
		MailWriteSearchResult teacher = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectTeacher");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				teacher = new MailWriteSearchResult(rset.getInt("USER_NO"),
												    rset.getString("USER_NAME"),
												    rset.getString("USER_ID"),
												    true);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return teacher;
	}
	
	public ArrayList<MailWriteSearchResult> selectStudentList(Connection conn) {
		ArrayList<MailWriteSearchResult> list = new ArrayList<MailWriteSearchResult>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectStudentList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new MailWriteSearchResult(rset.getInt("USER_NO"),
												   rset.getString("USER_NAME"),
												   rset.getString("USER_ID"),
												   true));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public ArrayList<MailWriteSearchResult> selectPublicContactsList(Connection conn) {
		ArrayList<MailWriteSearchResult> list = new ArrayList<MailWriteSearchResult>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectPublicContactsList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new MailWriteSearchResult(rset.getInt("CONTACTS_NO"),
												   rset.getString("CONTACTS_NAME"),
												   false));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public ArrayList<MailWriteSearchResult> selectPrivateContactsList(Connection conn, int ownerNo) {
		ArrayList<MailWriteSearchResult> list = new ArrayList<MailWriteSearchResult>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectPrivateContactsList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ownerNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new MailWriteSearchResult(rset.getInt("CONTACTS_NO"),
												   rset.getString("CONTACTS_NAME"),
												   false));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<MailReceiver> selectContactsMemberList(Connection conn, int contactsNo) {
		ArrayList<MailReceiver> list = new ArrayList<MailReceiver>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectContactsMemberList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, contactsNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new MailReceiver(rset.getInt("USER_NO"),
										  rset.getString("USER_NAME"),
										  rset.getString("USER_ID")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<MailReceiver> selectUserList(Connection conn) {
		ArrayList<MailReceiver> list = new ArrayList<MailReceiver>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectUserList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new MailReceiver(rset.getInt("USER_NO"),
						  rset.getString("USER_NAME"),
						  rset.getString("USER_ID")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<MailReceiver> selectAllStudentList(Connection conn) {
		ArrayList<MailReceiver> list = new ArrayList<MailReceiver>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectStudentList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new MailReceiver(rset.getInt("USER_NO"),
						  rset.getString("USER_NAME"),
						  rset.getString("USER_ID")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	// ===================== 메일 답장 관련 =============================
	
	public Mail selectMailtoReply(Connection conn, String mailNo) {
		Mail m = new Mail();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMailtoReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mailNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m.setMailNo(rset.getString("MAIL_NO"));
				m.setUserNo(rset.getInt("SENDER"));
				m.setUserName(rset.getString("USER_NAME"));
				m.setUserId(rset.getString("USER_ID"));
				m.setMailTitle(rset.getString("MAIL_TITLE"));
				m.setMailContent(rset.getString("MAIL_CONTENT"));
				m.setSendDate(rset.getString("SEND_DATE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return m;
	}

	public ArrayList<MailReceiver> selectMailReceiverOnlyR(Connection conn, String mailNo) {
		ArrayList<MailReceiver> list = new ArrayList<MailReceiver>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMailReceiverOnlyR");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mailNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new MailReceiver(rset.getString("MAIL_NO"),
										  rset.getInt("RECEIVER_NO"),
										  rset.getString("USER_NAME"),
										  rset.getString("USER_ID"),
										  rset.getString("RECEIVER_TYPE")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<MailReceiver> selectMailReceiverOnlyC(Connection conn, String mailNo) {
		ArrayList<MailReceiver> list = new ArrayList<MailReceiver>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMailReceiverOnlyC");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mailNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new MailReceiver(rset.getString("MAIL_NO"),
										  rset.getInt("RECEIVER_NO"),
										  rset.getString("USER_NAME"),
										  rset.getString("USER_ID"),
										  rset.getString("RECEIVER_TYPE")));
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
