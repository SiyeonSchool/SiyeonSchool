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

public class MailDao {

	private Properties prop = new Properties();
	
	public MailDao() {
		try {
			prop.loadFromXML(new FileInputStream(MailDao.class.getResource("/db/sql/mail-mapper.xml").getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int selectInboxMailListCount(Connection conn, int ownerNo) {
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectInboxMailListCount");
		
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
	
}
