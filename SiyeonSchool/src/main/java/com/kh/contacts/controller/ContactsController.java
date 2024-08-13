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
		
		ArrayList<ContactsCategory> categoryList = new ContactsService().selectCategoryList();
		ArrayList<Contacts> contactsList = new ContactsService().selectContactsList();
		ArrayList<User> userList = new ContactsService().selectUserList();
		
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("contactsList", contactsList);
		request.setAttribute("userList", userList);
		
		request.getSession().setAttribute("currentPage", "contacts");
		request.getRequestDispatcher("views/contacts/contacts.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
