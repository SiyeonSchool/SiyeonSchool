package com.kh.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.user.model.service.UserService;
import com.kh.user.model.vo.User;

/**
 * Servlet implementation class LoginUserController
 */
@WebServlet("/login.user")
public class LoginUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");

		User loginUser = new UserService().loginUser(userId, userPwd);

		if(loginUser != null) { // 로그인 성공
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);

			response.sendRedirect(request.getContextPath() + "/home");
		}else { // 로그인 실패 
	        request.setAttribute("loginFailed", true);
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
