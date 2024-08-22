package com.kh.contacts.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.contacts.model.service.ContactsService;

/**
 * Servlet implementation class AjaxContactsCategoryController
 */
@WebServlet("/contacts/categoryNo")
public class AjaxContactsCategoryNoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxContactsCategoryNoController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 주소록카테고리번호 조회 컨트롤러 : "주소록번호" 혹은 "카레고리명"으로 "카테고리번호" 조회
		
		int contactsNo = Integer.parseInt(request.getParameter("contactsNo"));
		int result = -1;
		
		if(contactsNo != 0) { // "주소록번호"가 있는경우
			result = new ContactsService().selectCategoryNoByContactsNo(contactsNo);
			
		}else { // 주소록 번호가 0인 경우 => "주소록번호"가 아닌, "카테고리명" 사용
			request.setCharacterEncoding("utf-8");
			String categoryName = request.getParameter("categoryName");
			result = new ContactsService().selectCategoryNoByCategoryName(categoryName);
		}
        
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
