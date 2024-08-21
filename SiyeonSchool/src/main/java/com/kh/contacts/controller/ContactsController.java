package com.kh.contacts.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.contacts.model.service.ContactsService;
import com.kh.contacts.model.vo.Contacts;
import com.kh.contacts.model.vo.ContactsCategory;
import com.kh.user.model.vo.User;

/**
 * Servlet implementation class ContactsController
 */
@WebServlet("/contacts")
public class ContactsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactsController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 주소록 메인화면을 띄우기 위한 컨트롤러
		
		// 현재 카테고리번호 -> JS에서 사용
		if(request.getParameter("categoryNo") != null) {
			int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
			request.setAttribute("categoryNo", categoryNo);
		}
		
		// 현재 주소록번호 -> JS에서 사용
		if(request.getParameter("contactsNo") != null) {
			int contactsNo = Integer.parseInt(request.getParameter("contactsNo"));
			request.setAttribute("contactsNo", contactsNo);
		}
		
		int ownerNo = ((User)(request.getSession().getAttribute("loginUser"))).getUserNo();
		
		ArrayList<ContactsCategory> categoryList = new ContactsService().selectCategoryList();
		ArrayList<Contacts> pivateContactsList = new ContactsService().selectPrivateContactsList(ownerNo);
	
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("pivateContactsList", pivateContactsList);
		
		request.getSession().setAttribute("currentPage", "contacts"); // 메뉴바에서 해당 메뉴의 아이콘 선택을 위한 값
		request.getRequestDispatcher("views/contacts/contacts.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
