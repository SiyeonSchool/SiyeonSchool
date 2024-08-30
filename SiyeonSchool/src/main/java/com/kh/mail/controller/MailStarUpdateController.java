package com.kh.mail.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mail.model.service.MailService;
import com.kh.user.model.vo.User;

/**
 * Servlet implementation class MailStarUpdateController
 */
@WebServlet("/mail.update.star")
public class MailStarUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailStarUpdateController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 메일 별(중요표시) 변경 컨트롤러
		
		int ownerNo = ((User)(request.getSession().getAttribute("loginUser"))).getUserNo();
		
		String mailbox = request.getParameter("mb"); // 현재 메일함
		String cpage = request.getParameter("cpage"); // 현재 페이지
	
		String mailNo = request.getParameter("m");   // 현재 메일
		String starYN = request.getParameter("s");       // 별 ("Y" or "N")
		
		// System.out.println("mailbox:" + mailbox + ", cpage:" + cpage + " // mailNo:" + mailNo + ", starYN:" + starYN);
		
		if(starYN.equals("Y")) {
			new MailService().updateStar("N", ownerNo, mailNo);
		}else {
			new MailService().updateStar("Y", ownerNo, mailNo);
		}
		
		if(cpage == null) { // 메일 상세조회 페이지
			response.sendRedirect(request.getContextPath() + "/mail.detail?mb=" + mailbox + "&m=" + mailNo);
		}else { // 메일목록 페이지
			response.sendRedirect(request.getContextPath() + "/mail?mb=" + mailbox + "&cpage=" + cpage);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
