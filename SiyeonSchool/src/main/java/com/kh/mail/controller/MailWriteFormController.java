package com.kh.mail.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mail.model.service.MailService;
import com.kh.mail.model.vo.Mail;
import com.kh.mail.model.vo.MailReceiver;
import com.kh.mail.model.vo.MailWriteSearchResult;
import com.kh.mail.model.vo.Mailbox;
import com.kh.user.model.vo.User;

/**
 * Servlet implementation class MailInsertController
 */
@WebServlet("/mail.writeForm")
public class MailWriteFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailWriteFormController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 메일쓰기 컨트롤러
		
		int ownerNo = ((User)(request.getSession().getAttribute("loginUser"))).getUserNo();

		// ===================== 사이드바 관련 =====================
		String currentMailbox = request.getParameter("mb"); // 현재 메일함
		
		// 기본메일함별 메일개수 리스트
		ArrayList<Mailbox> mailboxCountList = new MailService().selectMailboxCountList(ownerNo);
		
		int allMailCount = 0;
		for(int i=0; i<mailboxCountList.size(); i++) {
			if(i == 2 || i == 4) {
				continue; // index2:임시보관함, index4:휴지통
			}
			allMailCount += mailboxCountList.get(i).getMailCount();
		}
		
		int unreadMailCount = new MailService().selectUnreadMailCount(ownerNo); // 않읽은메일 개수
		int importantMailCount = new MailService().selectImportantMailCount(ownerNo); // 중요메일 개수

		// 내메일함별 메일 리스트
		ArrayList<Mailbox> pMailboxCountList = new MailService().selectPrivateMailboxCountList(ownerNo);

		request.setAttribute("currentMailbox", currentMailbox);     	// 현재메일함
		
		request.setAttribute("mailboxCountList", mailboxCountList); 	// 메일함별 메일개수 리스트 
		request.setAttribute("pMailboxCountList", pMailboxCountList); 	// 내메일함별 메일개수 리스트
		
		request.setAttribute("allMailCount", allMailCount);  		    // 전체메일개수
		request.setAttribute("unreadMailCount", unreadMailCount);  		// 안읽은메일개수
		request.setAttribute("importantMailCount", importantMailCount); // 중요메일개수
		
		// ===================== 수신인 검색에 필요한 데이터 =====================
		
		MailWriteSearchResult teacher = new MailService().selectTeacher(); // 선생님
		ArrayList<MailWriteSearchResult> studentList = new MailService().selectStudentList(); // 학생 리스트
		ArrayList<MailWriteSearchResult> contactsList = new MailService().selectContactsList(ownerNo); // 모든 주소록 (공유+개인)
		
		request.setAttribute("teacher", teacher); 	
		request.setAttribute("studentList", studentList);
		request.setAttribute("contactsList", contactsList);
		
		
		// ===================== 답장 관련 =====================
		String mailNo = request.getParameter("m"); // 답장할 메일번호
		if (mailNo != null) {
			Mail m = new MailService().selectMailtoReply(mailNo); // 메일정보
			ArrayList<MailReceiver> mrListR = new MailService().selectMailReceiverOnlyR(mailNo); // 수신인 리스트
			ArrayList<MailReceiver> mrListC = new MailService().selectMailReceiverOnlyC(mailNo); // 참조인 리스트
			
			request.setAttribute("m", m);
			request.setAttribute("mrListR", mrListR);
			request.setAttribute("mrListC", mrListC);
		}
		
		
		// ===================== 응답화면 =====================
		request.getRequestDispatcher("views/mail/mailWrtieForm.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
