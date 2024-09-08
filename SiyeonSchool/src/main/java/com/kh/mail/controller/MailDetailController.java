package com.kh.mail.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.Attachment;
import com.kh.mail.model.service.MailService;
import com.kh.mail.model.vo.Mail;
import com.kh.mail.model.vo.MailReceiver;
import com.kh.mail.model.vo.Mailbox;
import com.kh.user.model.vo.User;

/**
 * Servlet implementation class MailDetailController
 */
@WebServlet("/mail.detail")
public class MailDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailDetailController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 메일 상세조회 컨트롤러
		
		String currentMailbox = request.getParameter("mb"); // 현재 메일함
		String currentMailNo = request.getParameter("m"); // 현재 메일
		int ownerNo = ((User)(request.getSession().getAttribute("loginUser"))).getUserNo();
		
		// ===================== 메일 읽음처리 =====================	
		if(request.getParameter("ur") == null) {
			new MailService().updateIsRead(ownerNo, currentMailNo);
		}else {
			request.setAttribute("ur", request.getParameter("ur"));
		}
		
		
		// ===================== 사이드바 - 메일함 메일 개수 관련 =====================
		// 기본메일함별 메일개수 리스트
		ArrayList<Mailbox> mailboxCountList = new MailService().selectMailboxCountList(ownerNo);
		
		// 내메일함별 메일 리스트
		ArrayList<Mailbox> pMailboxCountList = new MailService().selectPrivateMailboxCountList(ownerNo);
			
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

	
		request.setAttribute("currentMailbox", currentMailbox);     	// 현재메일함
		
		request.setAttribute("mailboxCountList", mailboxCountList); 	// 메일함별 메일개수 리스트 
		request.setAttribute("pMailboxCountList", pMailboxCountList); 	// 내메일함별 메일개수 리스트
		
		request.setAttribute("allMailCount", allMailCount);  		    // 전체메일개수
		request.setAttribute("binMailCount", binMailCount);  		    // 휴지통메일개수
		request.setAttribute("unreadMailCount", unreadMailCount);  		// 안읽은메일개수
		request.setAttribute("importantMailCount", importantMailCount); // 중요메일개수

		// ===================== 메일상세조회 관련 =====================
		Mail m = new MailService().selectMail(ownerNo, currentMailNo);
		ArrayList<MailReceiver> mrList = new MailService().selectMailReceiverList(currentMailNo);
		HashMap<String, Integer> mrTypeCountMap = new MailService().selectMailReceiverTypeCount(currentMailNo);
		ArrayList<Attachment> attList = new MailService().selectAttachmentList(currentMailNo);
		
		request.setAttribute("mail", m);
		request.setAttribute("mailReceivers", mrList);
		request.setAttribute("mrTypeCountMap", mrTypeCountMap);
		request.setAttribute("attList", attList);

		request.getRequestDispatcher("views/mail/mailDetail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
