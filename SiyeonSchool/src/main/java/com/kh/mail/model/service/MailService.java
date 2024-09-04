package com.kh.mail.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import static com.kh.common.JDBCTemplate.*;

import com.kh.common.model.vo.Attachment;
import com.kh.common.model.vo.PageInfo;
import com.kh.mail.model.dao.MailDao;
import com.kh.mail.model.vo.Mail;
import com.kh.mail.model.vo.MailReceiver;
import com.kh.mail.model.vo.MailWriteSearchResult;
import com.kh.mail.model.vo.Mailbox;

public class MailService {
	
	// ===================== 메일 개수 조회 =============================
	
	public ArrayList<Mailbox> selectMailboxCountList(int ownerNo) {
		Connection conn = getConnection();
		ArrayList<Mailbox> list = new MailDao().selectMailboxCountList(conn, ownerNo);
		close(conn);
		return list;
	}
	
	public ArrayList<Mailbox> selectPrivateMailboxCountList(int ownerNo) {
		Connection conn = getConnection();
		ArrayList<Mailbox> list = new MailDao().selectPrivateMailboxCountList(conn, ownerNo);
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
	
	public ArrayList<Mail> selectPrivateMailboxMailList(String mailboxNo, PageInfo pi) {
		Connection conn = getConnection();
		ArrayList<Mail> list = new MailDao().selectPrivateMailboxMailList(conn, mailboxNo, pi);
		close(conn);
		return list;
	}

	// ===================== 메일 상세 조회 =============================
	
	// 메일 상세 조회
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

	public HashMap<String, Integer> selectMailReceiverTypeCount(String mailNo) {
		Connection conn = getConnection();
		HashMap<String, Integer> map = new MailDao().selectMailReceiverTypeCount(conn, mailNo);
		close(conn);
		return map;
	}

	public ArrayList<Attachment> selectAttachmentList(String mailNo) {
		Connection conn = getConnection();
		ArrayList<Attachment> list = new MailDao().selectAttachmentList(conn, mailNo);
		close(conn);
		return list;
	}

	// ===================== 별 수정 =============================
	
	public void updateStar(String starYN, int ownerNo, String mailNo) {
		Connection conn = getConnection();
		int result = new MailDao().updateStar(conn, starYN, ownerNo, mailNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
	}



	// ===================== 읽음 수정 =============================
	
	// 메일 읽음처리 - 목록조회에서 토글
	public void updateIsRead(int ownerNo, String mailNo) {
		Connection conn = getConnection();
		String isRead = new MailDao().selectIsRead(conn, ownerNo, mailNo);
		
		if(isRead == null || !isRead.equals("N")) { // 보낸메일, 읽은메일일 경우: 읽음처리 안함.
			return;
		}
		
		// 안읽은메일일 경우만 실행 (isRead.equals("N"))
		int result = new MailDao().updateReadToSysdate(conn, ownerNo, mailNo);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
	}
	
	// 메일 읽음처리 - 상세조회에서 토글
	public void updateRead(String readYN, int ownerNo, String mailNo) {
		Connection conn = getConnection();
		
		int result = 0;
		 
		if(readYN.equals("N")) {
			result = new MailDao().updateReadToSysdate(conn, ownerNo, mailNo); 
		}else {
			result = new MailDao().updateReadToNull(conn, ownerNo, mailNo); 
		}
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
	}
	
	// ===================== 메일 보내기 =============================
	
	public int insertMail(Mail m, Attachment at, ArrayList<MailReceiver> mrList, int loginUserNo) {
		Connection conn = getConnection();
		
		// 메일 DB에 추가
		int mailResult = new MailDao().insertMail(conn, m);
		
		// 첨부파일 DB에 추가
		int attachmentResult = 1;
		if(at != null) {
			attachmentResult = new MailDao().insertAttachment(conn, at);
		}
		
		// 메일수신인 DB에 추가
		int receiverResult = 1;
		for (MailReceiver mr: mrList) {
			receiverResult *= new MailDao().insertMailReceiver(conn, mr);
		}
		
		// 메일소유자 DB에 추가
		int ownerResult = 1;
		
		if(mrList.size() == 1 && mrList.get(0).getReceiverNo() == loginUserNo) { // 내게쓴메일
			String myselfMailboxNo = new MailDao().selectMyselfMailboxNo(conn, loginUserNo); // 발신인 -> 내게쓴메일함
			ownerResult *= new MailDao().insertMailOwner(conn, loginUserNo, myselfMailboxNo);
			
		} else { // 내게쓴메일이 아닌경우
			String sentMailboxNo = new MailDao().selectSentMailboxNo(conn, loginUserNo); // 발신인 -> 보낸메일함
			ownerResult *= new MailDao().insertMailOwner(conn, loginUserNo, sentMailboxNo);
			
			for (MailReceiver mr: mrList) {
				int receiverNo = mr.getReceiverNo();
				String inboxNo =  new MailDao().selectInboxNo(conn, receiverNo); // 수신인 -> 받은메일함
				ownerResult *= new MailDao().insertMailOwner(conn, receiverNo, inboxNo);
			}
		}

		
		if(mailResult > 0 && attachmentResult > 0 && receiverResult > 0 && ownerResult > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return mailResult * attachmentResult * receiverResult * ownerResult;
	}

	// ===================== 수신인 검색관련 =============================

	public ArrayList<MailWriteSearchResult> selectStudentList() {
		Connection conn = getConnection();
		ArrayList<MailWriteSearchResult> list = new MailDao().selectStudentList(conn);
		close(conn);
		return list;
	}
	
	public MailWriteSearchResult selectTeacher() {
		Connection conn = getConnection();
		MailWriteSearchResult teacher = new MailDao().selectTeacher(conn);
		close(conn);
		return teacher;
	}

	public ArrayList<MailWriteSearchResult> selectContactsList(int ownerNo) {
		Connection conn = getConnection();
		
		ArrayList<MailWriteSearchResult> list = new MailDao().selectPublicContactsList(conn); // 공유주소록
		list.addAll(new MailDao().selectPrivateContactsList(conn, ownerNo)); // 개인주소록 합치기
		
		close(conn);
		return list; // 공유주소록 + 개인주소록
	}
	
	public ArrayList<MailReceiver> selectContactsMemberList(int contactsNo) {
		Connection conn = getConnection();
		ArrayList<MailReceiver> list = new MailDao().selectContactsMemberList(conn, contactsNo);
		close(conn);
		return list;
	}

	public ArrayList<MailReceiver> selectUserList() {
		Connection conn = getConnection();
		ArrayList<MailReceiver> list = new MailDao().selectUserList(conn);
		close(conn);
		return list;
	}
	
	public ArrayList<MailReceiver> selectAllStudentList() {
		Connection conn = getConnection();
		ArrayList<MailReceiver> list = new MailDao().selectAllStudentList(conn);
		close(conn);
		return list;
	}

}
