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
 * Servlet implementation class MailDeleteToBinController
 */
@WebServlet("/mail.updateMailStatus")
public class MailUpdateMailStatusController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailUpdateMailStatusController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 메일을 휴지통으로 보내거나 복구하는 컨트롤러
		
		int loginUserNo = ((User)(request.getSession().getAttribute("loginUser"))).getUserNo();
		
		String mailBoxShortcut = request.getParameter("mb");
		String mailNo = request.getParameter("m");
		String mailStatus = request.getParameter("st"); //mailStatus "N"이면 휴지통으로 삭제, "Y"면 휴지통에서 복구

		String mailBoxNo = null;
		switch(mailBoxShortcut) {
		case "i": mailBoxNo = new MailService().selectInboxNo(loginUserNo); break; // 받은메일함
		case "s": mailBoxNo = new MailService().selectSentMailboxNo(loginUserNo); break; // 보낸메일함
		case "m": mailBoxNo = new MailService().selectMyselfMailboxNo(loginUserNo); break; // 내게쓴메일함
		case "t": mailBoxNo = new MailService().selectTempMailboxNo(loginUserNo); break; // 임시보관함
		}

		int result = 0;
		
		if(mailStatus.equals("N")) { // 메일 삭제
			result = new MailService().updateMailStatusToN(loginUserNo, mailBoxNo, mailNo);
		}else { // 메일 복구
			result = new MailService().updateMailStatusToY(loginUserNo, mailNo); // ## 문제점... 기존메일박스번호를 알수없음 -> 휴지통에 다른 메일함에서온 동일한 메일번호가 있는경우, 복구시 둘다 복구됨..
		}
		
		
		if(mailStatus.equals("N")) { // 메일 삭제
			if(result > 0) {
				request.getSession().setAttribute("mailAlertMsg", "성공적으로 메일을 삭제하였습니다. (휴지통으로 이동)");
			} else {
				request.getSession().setAttribute("mailAlertMsg", "메일삭제 실패");
			}
			
		}else { // 메일 복구
			if(result > 0) {
				request.getSession().setAttribute("mailAlertMsg", "성공적으로 메일을 복구하였습니다.");
			} else {
				request.getSession().setAttribute("mailAlertMsg", "메일복구 실패");
			}
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
