package com.kh.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.user.model.service.UserService;
import com.kh.user.model.vo.User;

/**
 * Servlet implementation class UserInsertController
 */
@WebServlet("/insert.user")
public class UserInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			request.setCharacterEncoding("utf-8");
			String userId = request.getParameter("userId"); 
			String userPwd = request.getParameter("userPwd");
			String userName = request.getParameter("userName");
			String phone = request.getParameter("phone");
			String birthday = request.getParameter("birthday");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			int questionNo = Integer.parseInt(request.getParameter("questionNo"));
			String questionAnswer = request.getParameter("questionAnswer");

			User u = new User(userId, userPwd, userName, phone, birthday, email, address, questionNo, questionAnswer);

			int result = new UserService().insertUser(u);

			if(result > 0) {
				
				HttpSession session = request.getSession();
				session.setAttribute("alertMsg", "관리자에게 회원가입 요청을 보냈습니다.");
				
				response.sendRedirect(request.getContextPath());

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
