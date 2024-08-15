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
public class AjaxContactsCategoryUsersList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxContactsCategoryUsersList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 현재사용자 번호 (로그인유저의 번호)
		int currentUserNo = ((User)(request.getSession().getAttribute("loginUser"))).getUserNo();

		// 카테고리번호로 해당 카테고리에 해당하는 주소록구성원리스트 조회
		int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));		
		ArrayList<User> list = new ContactsService().selectCategoryUsersList(currentUserNo, categoryNo);
		// currentUserNo를 넘겨주는 이유: 주소록에 각 사용자에 별표가 되어있는지 확인하려면, 현재 누가 로그인한건지 알아야함.
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(list, response.getWriter());
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
