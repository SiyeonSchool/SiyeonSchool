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

		int currentUserNo = ((User)(request.getSession().getAttribute("loginUser"))).getUserNo();
		String sortBy = request.getParameter("sortBy");
		System.out.println("sortBy: " + sortBy);
		
		System.out.println("categoryNo:" + request.getParameter("categoryNo"));
		System.out.println("contactsNo:" + request.getParameter("contactsNo"));
		
		/*
		sortBy: userName
		categoryNo:0
		contactsNo:
			
		sortBy: role
		categoryNo:2
		contactsNo:
			
		sortBy: birthday
		categoryNo:
		contactsNo:14
		*/
		
//		if(request.getParameter("categoryNo") != null) {
//			int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
//			System.out.println("categoryNo: " + categoryNo);
//			
//		}else if (request.getParameter("contactsNo") != null) {
//			int contactsNo = Integer.parseInt(request.getParameter("contactsNo"));
//			System.out.println("contactsNo: " + contactsNo);
//		}
//		
//		
		
//		ArrayList<User> list = new ContactsService().selectCategoryUsersList(currentUserNo, categoryNo);
//		
//		response.setContentType("application/json; charset=utf-8");
//		new Gson().toJson(list, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
