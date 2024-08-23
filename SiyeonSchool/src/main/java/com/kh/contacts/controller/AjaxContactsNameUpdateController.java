package com.kh.contacts.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.contacts.model.service.ContactsService;

/**
 * Servlet implementation class AjaxContactsNameUpdateController
 */
@WebServlet("/contacts/update.contactsName")
public class AjaxContactsNameUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxContactsNameUpdateController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 주소록명 수정 컨트롤러 : "주소록번호"로 해당하는 "주소록명" 수정
		
		request.setCharacterEncoding("utf-8");
		int contactsNo = Integer.parseInt(request.getParameter("contactsNo"));
		String newContactsName = request.getParameter("newContactsName");
		
		int result = new ContactsService().updateContactsName(contactsNo, newContactsName);
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
