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
import com.kh.contacts.model.vo.ContactsCategory;

/**
 * Servlet implementation class AjaxContactsCategoryListController
 */
@WebServlet("/contacts/list.category")
public class AjaxContactsCategoryListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxContactsCategoryListController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<ContactsCategory> categoryList = new ContactsService().selectCategoryList();
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(categoryList, response.getWriter());
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
