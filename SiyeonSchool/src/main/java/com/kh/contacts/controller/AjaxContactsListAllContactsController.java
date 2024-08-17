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
import com.kh.contacts.model.vo.Contacts;
import com.kh.user.model.vo.User;

/**
 * Servlet implementation class AjaxContactsListAllContactsController
 */
@WebServlet("/contacts/list.allContacts")
public class AjaxContactsListAllContactsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxContactsListAllContactsController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 모든주소록 목록조회용 컨트롤러 : OWNER_NO가 소유하고 있는 모든주소록 조회
		
		int currentUserNo = ((User)(request.getSession().getAttribute("loginUser"))).getUserNo();

		ArrayList<Contacts> list = new ContactsService().selectContactsList(currentUserNo);
		
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
