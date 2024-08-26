package com.kh.mail.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;
import com.kh.common.model.vo.PageInfo;
import com.kh.mail.model.dao.MailDao;
import com.kh.mail.model.vo.Mail;

public class MailService {
	
	public int selectInboxMailListCount(int ownerNo) {
		Connection conn = getConnection();
		int result = new MailDao().selectInboxMailListCount(conn, ownerNo);
		close(conn);
		return result;
		
	}
	
	public int selectSentMailListCount(int ownerNo) {
		Connection conn = getConnection();
		int result = new MailDao().selectSentMailListCount(conn, ownerNo);
		close(conn);
		return result;
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








}
