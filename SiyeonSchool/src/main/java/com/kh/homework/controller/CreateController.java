package com.kh.homework.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.homework.model.service.HomeworkService;
import com.kh.homework.model.vo.Subject;
import com.kh.user.model.service.UserService;
import com.kh.user.model.vo.Question;

/**
 * Servlet implementation class CreateController
 */
@WebServlet("/create.homework")
public class CreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Subject> subjectList = new HomeworkService().selectSubject();

		if (subjectList == null || subjectList.isEmpty()) {
			System.out.println("질문 리스트가 비어있습니다.");
		} else {
			for (Subject s : subjectList) {
			}
		}

		request.setAttribute("subjectList", subjectList);
		request.getRequestDispatcher("views/homework/create.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
