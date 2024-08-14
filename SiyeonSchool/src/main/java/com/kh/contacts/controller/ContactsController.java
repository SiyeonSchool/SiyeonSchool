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
		
		int ownerNo = 1; // 나중에 사용자번호 받아올 예정 (로그인 구현후)
		
		// 카테고리 조회
		ArrayList<ContactsCategory> categoryList = new ContactsService().selectCategoryList();
		
		// 개인주소록 목록조회
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
