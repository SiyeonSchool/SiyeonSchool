package com.kh.contacts.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.contacts.model.service.ContactsService;
import com.kh.contacts.model.vo.ContactsUsersSortInfo;
import com.kh.user.model.vo.User;

/**
 * Servlet implementation class AjaxContactsSortUsersListController
 */
@WebServlet("/contacts/list.orderBy")
public class AjaxContactsSortUsersListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxContactsSortUsersListController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 주소록구성원 "정렬"조회용 컨트롤러
		
		int currentUserNo = ((User)(request.getSession().getAttribute("loginUser"))).getUserNo();
		String orderBy = request.getParameter("sortBy"); // star, userName, userId, role, birthday, phone
		boolean isDesc = Boolean.parseBoolean(request.getParameter("isDesc"));
		
		ArrayList<User> list = null;
				
		if(request.getParameter("categoryNo").length() > 0) {
			int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
			
			if(categoryNo == 0) { // "전체사용자" 정렬
				ContactsUsersSortInfo si = new ContactsUsersSortInfo(currentUserNo, 0, 0, orderBy, isDesc); // categoryNo & contactsNo를 0으로 넘김
				list = new ContactsService().selectAllUsersListOrderBy(si);
						
			}else { // "주소록카테고리구성원" 정렬
				ContactsUsersSortInfo si = new ContactsUsersSortInfo(currentUserNo, categoryNo, 0, orderBy, isDesc);  // contactsNo를 0으로 넘김
				list = new ContactsService().selectCategoryUsersListOrderBy(si);
			}
			
		}else { // "주소록구성원" 정렬
			int contactsNo = Integer.parseInt(request.getParameter("contactsNo"));
			ContactsUsersSortInfo si = new ContactsUsersSortInfo(currentUserNo, 0, contactsNo, orderBy, isDesc); // categoryNo를 0으로 넘김
			list = new ContactsService().selectContactsUsersListOrderBy(si);
		}
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(list, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
