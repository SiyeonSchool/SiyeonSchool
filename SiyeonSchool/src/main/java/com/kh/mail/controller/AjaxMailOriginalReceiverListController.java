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
 * Servlet implementation class AjaxOriginalReceiverListController
 */
@WebServlet("/mail.wrtieForm/list.originalReceiver")
public class AjaxMailOriginalReceiverListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxMailOriginalReceiverListController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 기존메일 수신인 조회 컨트롤러 - 메일 전체답장, 전달시 사용됨.
		
		String mailNo = request.getParameter("mailNo"); // 메일번호
		
		ArrayList<MailReceiver> list = new MailService().selectOriginalReceiverList(mailNo);
	
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
