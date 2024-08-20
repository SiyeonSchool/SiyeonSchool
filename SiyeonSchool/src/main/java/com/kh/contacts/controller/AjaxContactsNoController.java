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
 * Servlet implementation class AjaxContactsNoController
 */
@WebServlet("/contacts/contactsNo")
public class AjaxContactsNoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxContactsNoController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 주소록번호 조회 컨트롤러 (주소록이름 + 유저번호로 검색)
		
		request.setCharacterEncoding("utf-8");
		String contactsName = request.getParameter("contactsName");
		
		int ownerNo = ((User)(request.getSession().getAttribute("loginUser"))).getUserNo();
		
		int contactNo = new ContactsService().selectContactNo(contactsName, ownerNo);
		
		response.getWriter().print(contactNo);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
