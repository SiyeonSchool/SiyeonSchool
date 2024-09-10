package com.kh.homework.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.homework.model.service.HomeworkService;

/**
 * Servlet implementation class addFolderController
 */
@WebServlet("/addFolder.homework")
public class addFolderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addFolderController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    String subjectName = request.getParameter("folderName");
	    
	    System.out.println("Received folderName: " + subjectName);

	    int result = new HomeworkService().addSubject(subjectName);

	    response.setContentType("text/html; charset=UTF-8");
	    if(result > 0) {
	        response.getWriter().write("success");
	    } else {
	        response.getWriter().write("fail");
	    }
	}

}
