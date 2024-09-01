package com.kh.mail.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.mail.model.service.MailService;
import com.kh.mail.model.vo.MailReceiver;

/**
 * Servlet implementation class AjaxMailContactsMemberListController
 */
@WebServlet("/mail.wrtieForm/list.contactsMember")
public class AjaxMailContactsMemberListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxMailContactsMemberListController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 주소록구성원 조회 컨트롤러 (메일쓰기에서 수신인을 주소록으로 검색시 사용됨)
		
		System.out.println("AjaxMailContactsMemberListController() 실행됨");
		
		int contactsNo = Integer.parseInt(request.getParameter("contactsNo"));
		
		ArrayList<MailReceiver> list = new MailService().selectContactsMemberList(contactsNo);
		
		System.out.println("-------컨트롤러 조회 결과-------");
		for(MailReceiver mr : list) {
			System.out.println(mr);
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
