package com.kh.classroom.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.classroom.model.service.ClassroomService;
import com.kh.classroom.model.vo.ClassPost;
import com.kh.common.model.vo.PageInfo;

/**
 * Servlet implementation class ClassController
 */
@WebServlet("/classroom")
public class ClassroomController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassroomController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 페이징 처리
		int listCount;  // 현재 총 게시글 개수
		int cPage;      // 현재 페이지
		int pageLimit;  // 페이징바 페이지 최대 개수
		int boardLimit; // 한 페이지에 보여질 게시글 최대개수
		
		int maxPage;   // 총 페이지수
		int startPage; // 페이징바 시작수
		int endPage;   // 페이징바 끝수
		
		listCount = new ClassroomService().selectAllListCount();
		cPage = Integer.parseInt(request.getParameter("cpage"));
		pageLimit = 10;
		boardLimit = 10;
		
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		startPage = (cPage - 1) / pageLimit * pageLimit + 1;
		endPage = startPage + pageLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(listCount, cPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		ArrayList<ClassPost> list = new ClassroomService().selectAllList(pi);
		
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		
		request.getSession().setAttribute("currentPage", "classroom");
		request.getRequestDispatcher("views/classroom/classroom.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
