package com.kh.mail.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.classroom.model.service.ClassroomService;
import com.kh.classroom.model.vo.ClassPost;
import com.kh.common.model.vo.PageInfo;
import com.kh.mail.model.service.MailService;
import com.kh.mail.model.vo.Mail;
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
		// 메일 메뉴클릭시, 메인화면으로 "받은메일함"을 메인 페이지로 사용!

		int ownerNo = ((User)(request.getSession().getAttribute("loginUser"))).getUserNo();
		
		// 페이징 처리
		int listCount = 0;  // 현재 총 게시글 개수
		int cPage;      // 현재 페이지
		int pageLimit;  // 페이징바 페이지 최대 개수
		int boardLimit; // 한 페이지에 보여질 게시글 최대개수
		
		int maxPage;   // 총 페이지수
		int startPage; // 페이징바 시작수
		int endPage;   // 페이징바 끝수
		
		// 각 메일함에 맞는 메일 개수 db에서 가져오기
		String currentMailbox = request.getParameter("mb"); // 현재 메일함
		switch(currentMailbox) {
			case "a": listCount = new MailService().selectInboxMailListCount(ownerNo); break; // a(all: 전체메일함)       // ######### 업데이트 필요
			case "i": listCount = new MailService().selectInboxMailListCount(ownerNo); break; // i(inbox: 받은메일함)
			case "s": listCount = new MailService().selectSentMailListCount(ownerNo); break; // s(sent: 보낸메일함)
			case "t": listCount = new MailService().selectInboxMailListCount(ownerNo); break; // t(temp: 임시보관함)    	// ######### 업데이트 필요
			case "m": listCount = new MailService().selectInboxMailListCount(ownerNo); break; // m(myself: 내게쓴메일함)   // ######### 업데이트 필요
			case "b": listCount = new MailService().selectInboxMailListCount(ownerNo); break; // b(bin:휴지통)   			// ######### 업데이트 필요
			case "u": listCount = new MailService().selectInboxMailListCount(ownerNo); break; // u(unread: 안읽은메일)    // ######### 업데이트 필요
			case "im": listCount = new MailService().selectInboxMailListCount(ownerNo); break; // im(important:중요메일)  // ######### 업데이트 필요
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
		
		// db에서 메일리스트 가져오기
		ArrayList<Mail> list = null;
		switch(currentMailbox) {
			case "a": ; list = new MailService().selectInboxMailList(ownerNo, pi); break;  // ######### 업데이트 필요
			case "i": ; list = new MailService().selectInboxMailList(ownerNo, pi); break;
			case "s": ; list = new MailService().selectSentMailList(ownerNo, pi); break;
			case "t": ; list = new MailService().selectInboxMailList(ownerNo, pi); break;  // ######### 업데이트 필요
			case "m": ; list = new MailService().selectInboxMailList(ownerNo, pi); break;  // ######### 업데이트 필요
			case "b": ; list = new MailService().selectInboxMailList(ownerNo, pi); break;  // ######### 업데이트 필요
			case "u": ; list = new MailService().selectInboxMailList(ownerNo, pi); break;  // ######### 업데이트 필요
			case "im": ; list = new MailService().selectInboxMailList(ownerNo, pi); break; // ######### 업데이트 필요
		}
		
		 
		
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		
		// 화면에 현재 메일함이 뭔지 전달해주기
		switch(currentMailbox) {
			case "a": request.setAttribute("currentMailbox", "a"); break;
			case "i": request.setAttribute("currentMailbox", "i"); break;
			case "s": request.setAttribute("currentMailbox", "s"); break;
			case "t": request.setAttribute("currentMailbox", "t"); break;
			case "m": request.setAttribute("currentMailbox", "m"); break;
			case "b": request.setAttribute("currentMailbox", "b"); break;
			case "u": request.setAttribute("currentMailbox", "u"); break;
			case "im": request.setAttribute("currentMailbox", "im"); break;
		}
		
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
