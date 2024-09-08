package com.kh.mail.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mail.model.service.MailService;
import com.kh.user.model.vo.User;

/**
 * Servlet implementation class AjaxMailStatusUpdateByListController
 */
@WebServlet("/mail.updateMailStatusByList")
public class AjaxMailStatusUpdateByListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxMailStatusUpdateByListController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 메일목록에서 체크박스로 선택한 메일들을 휴지통으로 보내는 컨트롤러
		
		int loginUserNo = ((User)(request.getSession().getAttribute("loginUser"))).getUserNo();
		
		String mailBoxNo = request.getParameter("mailboxNo");
		String mailNoListString = request.getParameter("mailNoList");

		String[] strList = mailNoListString.replace("[", "").replace("]", "").split(",");

		ArrayList<String> mailNoList = new ArrayList<String>(); 
		System.out.println("-------컨트롤러 mNo------");
		for(String mNo : strList) {
			System.out.println(mNo);
		    mailNoList.add(mNo.trim());
		}
		
		System.out.println("-----------------");
		for (String m : mailNoList) {
		    System.out.println(m); // Prints M511, M508, M507 without quotes
		}
		
		int result = new MailService().updateMailStatusByList(loginUserNo, mailBoxNo, mailNoList);
		System.out.println(result);
		response.getWriter().print(result);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
