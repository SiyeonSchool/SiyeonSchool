package com.kh.contacts.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.contacts.model.service.ContactsService;

/**
 * Servlet implementation class AjaxContactsCategoryNameUpdateController
 */
@WebServlet("/contacts/update.categoryName")
public class AjaxContactsCategoryNameUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxContactsCategoryNameUpdateController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 주소록카테고리명 변경 컨트롤러 : "카테고리번호"로 "카테고리명"변경
		
		request.setCharacterEncoding("utf-8");
		int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
		String newCategoryName = request.getParameter("newCategoryName");
		
		int result = new ContactsService().updateCategoryName(categoryNo, newCategoryName);
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
