package com.kh.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.user.model.service.UserService;

/**
 * Servlet implementation class UpdateUserPwdController
 */
@WebServlet("/UpdateUserPwd.user")
public class UpdateUserPwdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserPwdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId");
        String updatePwd = request.getParameter("userPwd");
        String confirmPwd = request.getParameter("confirmPwd");

        if (updatePwd != null && updatePwd.equals(confirmPwd)) {
            UserService us = new UserService();
            int result = us.updateUserPwd(userId, updatePwd);

            if (result > 0) {
            	request.setAttribute("message", "비밀번호 변경 완료");
                response.sendRedirect("index.jsp");
            } else {
                request.setAttribute("message", "비밀번호 변경에 실패했습니다.");
                request.getRequestDispatcher("views/home/userIdPwdFind.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", "비밀번호가 일치하지 않습니다.");
            request.getRequestDispatcher("views/home/userIdPwdFind.jsp").forward(request, response);
        }
    }
	
}
