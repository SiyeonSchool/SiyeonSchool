package com.kh.mail.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;
import com.kh.common.model.vo.PageInfo;
import com.kh.mail.model.dao.MailDao;
import com.kh.mail.model.vo.Mail;
import com.kh.mail.model.vo.MailReceiver;
import com.kh.mail.model.vo.Mailbox;

public class MailService {
	
	// ===================== 메일 개수 조회 =============================
	
	public ArrayList<Mailbox> selectMailboxCountList(int ownerNo) {
		Connection conn = getConnection();
		ArrayList<Mailbox> list = new MailDao().selectMailboxCountList(conn, ownerNo);
		close(conn);
		return list;
	}
	
	public int selectUnreadMailCount(int ownerNo) {
		Connection conn = getConnection();
		int result = new MailDao().selectUnreadMailCount(conn, ownerNo);
		close(conn);
		return result;
	}

	public int selectImportantMailCount(int ownerNo) {
		Connection conn = getConnection();
		int result = new MailDao().selectImportantMailCount(conn, ownerNo);
		close(conn);
		return result;
	}
	
	// ===================== 메일 목록 조회 =============================
	
	public ArrayList<Mail> selectAllMailList(int ownerNo, PageInfo pi) {
		Connection conn = getConnection();
		ArrayList<Mail> list = new MailDao().selectAllMailList(conn, ownerNo, pi);
		close(conn);
		return list;
	}
	
	public ArrayList<Mail> selectInboxMailList(int ownerNo, PageInfo pi) {
		Connection conn = getConnection();
		ArrayList<Mail> list = new MailDao().selectInboxMailList(conn, ownerNo, pi);
		close(conn);
		return list;
	}

	public ArrayList<Mail> selectSentMailList(int ownerNo, PageInfo pi) {
		Connection conn = getConnection();
		ArrayList<Mail> list = new MailDao().selectSentMailList(conn, ownerNo, pi);
		close(conn);
		return list;
	}

	public ArrayList<Mail> selectTempMailList(int ownerNo, PageInfo pi) {
		Connection conn = getConnection();
		ArrayList<Mail> list = new MailDao().selectTempMailList(conn, ownerNo, pi);
		close(conn);
		return list;
	}

	public ArrayList<Mail> selectToMyselfMailList(int ownerNo, PageInfo pi) {
		Connection conn = getConnection();
		ArrayList<Mail> list = new MailDao().selectToMyselfMailList(conn, ownerNo, pi);
		close(conn);
		return list;
	}

	public ArrayList<Mail> selectBinMailList(int ownerNo, PageInfo pi) {
		Connection conn = getConnection();
		ArrayList<Mail> list = new MailDao().selectBinMailList(conn, ownerNo, pi);
		close(conn);
		return list;
	}

	public ArrayList<Mail> selectUnreadMailList(int ownerNo, PageInfo pi) {
		Connection conn = getConnection();
		ArrayList<Mail> list = new MailDao().selectUnreadMailList(conn, ownerNo, pi);
		close(conn);
		return list;
	}

	public ArrayList<Mail> selectImportantMailList(int ownerNo, PageInfo pi) {
		Connection conn = getConnection();
		ArrayList<Mail> list = new MailDao().selectImportantMailList(conn, ownerNo, pi);
		close(conn);
		return list;
	}

	// ===================== 메일 상세 조회 =============================
	
	public Mail selectMail(int ownerNo, String mailNo) {
		Connection conn = getConnection();
		Mail m = new MailDao().selectMail(conn, ownerNo, mailNo);
		close(conn);
		return m;
	}

	public ArrayList<MailReceiver> selectMailReceiverList(String mailNo) {
		Connection conn = getConnection();
		ArrayList<MailReceiver> list = new MailDao().selectMailReceiverList(conn, mailNo);
		close(conn);
		return list;
	}

}
