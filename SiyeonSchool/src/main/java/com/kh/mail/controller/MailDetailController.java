package com.kh.mail.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mail.model.service.MailService;
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
		
		String currentMailbox = request.getParameter("mb"); // 현재 메일함
		String currentMail = request.getParameter("m"); // 현재 메일\
		System.out.println("currentMail:" + currentMail);
		
		int ownerNo = ((User)(request.getSession().getAttribute("loginUser"))).getUserNo();
		
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
		
		request.setAttribute("currentMailbox", currentMailbox);     	// 현재메일함
		
		request.setAttribute("mailboxCountList", mailboxCountList); 	// 메일함별 메일개수 리스트 
		request.setAttribute("allMailCount", allMailCount);  		    // 전체메일개수
		request.setAttribute("unreadMailCount", unreadMailCount);  		// 안읽은메일개수
		request.setAttribute("importantMailCount", importantMailCount); // 중요메일개수

		request.getRequestDispatcher("views/mail/mailDetail.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
