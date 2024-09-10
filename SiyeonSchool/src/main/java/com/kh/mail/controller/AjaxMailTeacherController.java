package com.kh.mail.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.mail.model.service.MailService;
import com.kh.mail.model.vo.MailWriteSearchResult;

/**
 * Servlet implementation class AjaxMailAllUsersListController
 */
@WebServlet("/mail.wrtieForm/teacher")
public class AjaxMailTeacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxMailTeacherController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MailWriteSearchResult teacher = new MailService().selectTeacher();
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(teacher, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
