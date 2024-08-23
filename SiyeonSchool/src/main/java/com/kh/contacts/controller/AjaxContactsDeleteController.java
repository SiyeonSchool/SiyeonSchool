package com.kh.contacts.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.contacts.model.service.ContactsService;

/**
 * Servlet implementation class AjaxContactsDeleteController
 */
@WebServlet("/contacts/delete.contacts")
public class AjaxContactsDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxContactsDeleteController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 주소록구성원 삭제 컨트롤러 : "특정주소록"에 "특정유저"를 삭제
		
		int contactsNo = Integer.parseInt(request.getParameter("contactsNo"));
        int result = new ContactsService().deleteContacts(contactsNo);
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
