package com.kh.mail.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.PageInfo;
import com.kh.mail.model.service.MailService;
import com.kh.mail.model.vo.Mail;
import com.kh.mail.model.vo.Mailbox;
import com.kh.user.model.vo.User;

/**
 * Servlet implementation class ContactsController
 */
@WebServlet("/mail")
public class MailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// "메일" 메인화면 컨트롤러

		
		// ===================== 필요한 데이터 전달받기 =============================
		// 전달받는 "mb"(mailBox)의 parameter 값에 따라 화면에 해당하는 리스트를 보여줌.
		String currentMailbox = request.getParameter("mb"); // 현재 메일함
		// a(all: 전체메일함), i(inbox: 받은메일함), s(sent: 보낸메일함), t(temp: 임시보관함), m(myself: 내게쓴메일함), b(bin:휴지통)
		// u(unread: 안읽은메일), im(important:중요메일)
		
		int ownerNo = ((User)(request.getSession().getAttribute("loginUser"))).getUserNo();
		
		
		// ===================== 메일 개수 조회 =============================
		// 기본메일함별 메일개수 리스트
		ArrayList<Mailbox> mailboxCountList = new MailService().selectMailboxCountList(ownerNo);
		/*
		 * mailboxCountList 예시)
		 	Mailbox [mailboxNo=MB7, mailboxName=받은메일함, mailCount=501]
		 	Mailbox [mailboxNo=MB8, mailboxName=보낸메일함, mailCount=1]
	     	Mailbox [mailboxNo=MB9, mailboxName=임시보관함, mailCount=0]
			Mailbox [mailboxNo=MB10, mailboxName=내게쓴메일함, mailCount=0]
			Mailbox [mailboxNo=MB11, mailboxName=휴지통, mailCount=0]
			Mailbox [mailboxNo=MB12, mailboxName=내 메일함, mailCount=0]
		 */

		// 내메일함별 메일 리스트
		ArrayList<Mailbox> pMailboxCountList = new MailService().selectPrivateMailboxCountList(ownerNo);
		
		// 전체메일 개수: 임시보관함과 휴지통을 제외한 나머지를 더함.
		int allMailCount = 0;
		for(int i=0; i<mailboxCountList.size(); i++) {
			if(i == 2 || i == 4) {
				continue; // index2:임시보관함, index4:휴지통
			}
			allMailCount += mailboxCountList.get(i).getMailCount();
		}
		
		int binMailCount = new MailService().selectBinMailCount(ownerNo); // 휴지통메일 개수
		int unreadMailCount = new MailService().selectUnreadMailCount(ownerNo); // 않읽은메일 개수
		int importantMailCount = new MailService().selectImportantMailCount(ownerNo); // 중요메일 개수
		

		// ===================== 페이징 처리 =============================
		int listCount = 0;  // 현재 총 게시글 개수
		int cPage;      // 현재 페이지
		int pageLimit;  // 페이징바 페이지 최대 개수
		int boardLimit; // 한 페이지에 보여질 게시글 최대개수
		
		int maxPage;   // 총 페이지수
		int startPage; // 페이징바 시작수
		int endPage;   // 페이징바 끝수
		
		switch(currentMailbox) {
			case "a": listCount = allMailCount; break; // 전체메일함
			case "i": listCount = mailboxCountList.get(0).getMailCount(); break; // 받은메일함
			case "s": listCount = mailboxCountList.get(1).getMailCount(); break; // 보낸메일함
			case "t": listCount = mailboxCountList.get(2).getMailCount(); break; // 임시보관함
			case "m": listCount = mailboxCountList.get(3).getMailCount(); break; // 내게쓴메일함
			case "b": listCount = binMailCount; break; // 휴지통
			case "u": listCount = unreadMailCount; break;  // 안읽은메일
			case "im": listCount = unreadMailCount; break; // 중요메일
			default: // 내메일함
				for(Mailbox mb : pMailboxCountList) {
					if(mb.getMailboxNo().equals(currentMailbox)) {
						listCount = mb.getMailCount();
						break;
					}
				}
		}
		
		cPage = Integer.parseInt(request.getParameter("cpage"));
		pageLimit = 10;
		boardLimit = 20;
		
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		startPage = (cPage - 1) / pageLimit * pageLimit + 1;
		endPage = startPage + pageLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(listCount, cPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		
		// ===================== db에서 메일리스트 가져오기 =============================
		ArrayList<Mail> list = null;
		switch(currentMailbox) {
			case "a": list = new MailService().selectAllMailList(ownerNo, pi); break;        // 전체메일함
			case "i": list = new MailService().selectInboxMailList(ownerNo, pi); break; 	   // 받은메일함
			case "s": list = new MailService().selectSentMailList(ownerNo, pi); break; 	   // 보낸메일함
			case "t": list = new MailService().selectTempMailList(ownerNo, pi); break;       // 임시보관함
			case "m": list = new MailService().selectToMyselfMailList(ownerNo, pi); break;   // 내게쓴메일함
			case "b": list = new MailService().selectBinMailList(ownerNo, pi); break;        // 휴지통
			case "u": list = new MailService().selectUnreadMailList(ownerNo, pi); break;     // 안읽은메일
			case "im": list = new MailService().selectImportantMailList(ownerNo, pi); break; // 중요메일
			default : list = new MailService().selectPrivateMailboxMailList(currentMailbox, pi); break; // 내메일함
		}

		
		// ===================== jsp로 값 전달 =============================
		request.setAttribute("pi", pi);     // pageInfo - 페이징 처리관련 객체
		request.setAttribute("list", list); // 화면에 보여줄 메일리스트
		
		request.setAttribute("currentMailbox", currentMailbox);     	// 현재메일함
		
		request.setAttribute("mailboxCountList", mailboxCountList); 	// 기본메일함별 메일개수 리스트
		request.setAttribute("pMailboxCountList", pMailboxCountList); 	// 내메일함별 메일개수 리스트
		
		request.setAttribute("allMailCount", allMailCount);  		    // 전체메일개수
		request.setAttribute("binMailCount", binMailCount);  		    // 휴지통메일개수
		request.setAttribute("unreadMailCount", unreadMailCount);  		// 안읽은메일개수
		request.setAttribute("importantMailCount", importantMailCount); // 중요메일개수
		
		
		request.getSession().setAttribute("currentPage", "mail");
		request.getRequestDispatcher("views/mail/mail.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
