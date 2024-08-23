package com.kh.contacts.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.contacts.model.service.ContactsService;

/**
 * Servlet implementation class AjaxPublicContactsInsertController
 */
@WebServlet("/contacts/insert.publicContacts")
public class AjaxPublicContactsInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxPublicContactsInsertController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공유주소록을 추가하는 컨트롤러 (관리자만 가능)
		
		request.setCharacterEncoding("utf-8");
        int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
        String contactsName = request.getParameter("contactsName");

        int result = new ContactsService().insertPublicContacts(categoryNo, contactsName);
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
