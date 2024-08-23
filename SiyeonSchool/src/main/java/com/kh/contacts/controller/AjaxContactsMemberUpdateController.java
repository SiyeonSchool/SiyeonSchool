package com.kh.contacts.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.contacts.model.service.ContactsService;

/**
 * Servlet implementation class AjaxContactsMemberUpdateController
 */
@WebServlet("/contacts/update.member")
public class AjaxContactsMemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxContactsMemberUpdateController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 주소록구성원 변경 컨트롤러 : "팀장으로 지정"기능. (해당주소록 모든 유저를 F로 변경후, 전달받은 userNo만 L로 변경)
		
		int contactsNo = Integer.parseInt(request.getParameter("contactsNo"));
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		
		int result = new ContactsService().updateContactsMember(contactsNo, userNo);
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
