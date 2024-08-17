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
 * Servlet implementation class AjaxContactsCategoryUsersList
 */
@WebServlet("/contacts/list.categoryUsers")
public class AjaxContactsListCategoryUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxContactsListCategoryUsers() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 카테고리구성원 조회용 컨트롤러 - 카테고리구성원 조회: ex) 세미 1조~5조 전체구성원 (모든 팀장 + 팀원)
		
		int currentUserNo = ((User)(request.getSession().getAttribute("loginUser"))).getUserNo();
		int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));		
		
		ArrayList<User> list = new ContactsService().selectCategoryUsersList(currentUserNo, categoryNo);
		
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
