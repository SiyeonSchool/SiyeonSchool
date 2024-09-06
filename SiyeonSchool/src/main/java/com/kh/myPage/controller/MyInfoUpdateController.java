package com.kh.myPage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.myPage.model.service.MyPageService;
import com.kh.user.model.vo.User;

/**
 * Servlet implementation class MyInfoUpdateController
 */
@WebServlet("/myInfo.update")
public class MyInfoUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyInfoUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String userName = request.getParameter("userName");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String githubUrl = request.getParameter("githubUrl");
        String notionUrl = request.getParameter("notionUrl");
		int userNo = Integer.parseInt(request.getParameter("userNo"));

		User u = new User();
		u.setUserName(userName);
		u.setPhone(phone);
		u.setAddress(address);
		u.setEmail(email);
		u.setGithubUrl(githubUrl);
		u.setNotionUrl(notionUrl);
		u.setUserNo(userNo);

		User updateUser = new MyPageService().updateMyInfo(u);

		if(updateUser != null){
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", updateUser);
            response.sendRedirect(request.getContextPath() + "/myInfo.list");
		} else{
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
