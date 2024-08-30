package com.kh.myPage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.myPage.model.service.MyPageService;
import com.kh.myPage.model.vo.Attendance;
import com.kh.user.model.vo.User;

/**
 * Servlet implementation class AjaxAtdStateListController
 */
@WebServlet("/atdState.li")
public class AjaxAtdStateListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxAtdStateListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userNo = ((User) (request.getSession().getAttribute("loginUser"))).getUserNo();
		String currentMonth = request.getParameter("currentMonth");

		if(currentMonth != null && !currentMonth.isEmpty()){
			ArrayList<Attendance> atd = new MyPageService().selectAtd(userNo, currentMonth);
			response.setContentType("application/json; charset=utf-8");
			
			new Gson().toJson(atd, response.getWriter());
			System.out.println(atd);
		}
		
		
		
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
