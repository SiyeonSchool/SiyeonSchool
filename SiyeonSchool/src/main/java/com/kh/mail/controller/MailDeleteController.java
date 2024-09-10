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
 * Servlet implementation class MailDeleteController
 */
@WebServlet("/mail.delete")
public class MailDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailDeleteController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int loginUserNo = ((User)(request.getSession().getAttribute("loginUser"))).getUserNo();
		
		String mailNo = request.getParameter("m");
		
		int result = new MailService().deleteMail(loginUserNo, mailNo);
		
		if(result > 0) {
			request.getSession().setAttribute("mailAlertMsg", "성공적으로 메일을 영구삭제하였습니다.");
		} else {
			request.getSession().setAttribute("mailAlertMsg", "메일 영구삭제 실패");
		}
		
		response.sendRedirect(request.getContextPath() + "/mail?mb=b&cpage=1"); // 휴지통으로
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
