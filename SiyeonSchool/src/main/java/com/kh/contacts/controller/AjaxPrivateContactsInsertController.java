package com.kh.contacts.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.contacts.model.service.ContactsService;
import com.kh.user.model.vo.User;

/**
 * Servlet implementation class AjaxPrivateContactsInsertController
 */
@WebServlet("/contacts/insert.privateContacts")
public class AjaxPrivateContactsInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxPrivateContactsInsertController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 새로운 주소록 추가용 컨트롤러
		
		String contactsName = request.getParameter("contactsName");
		int ownerNo = ((User)(request.getSession().getAttribute("loginUser"))).getUserNo();
		
		int result = new ContactsService().insertPrivateContacts(contactsName, ownerNo);
		
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
