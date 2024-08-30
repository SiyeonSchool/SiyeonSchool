package com.kh.homework.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.kh.common.MyFileRenamePolicy;
import com.kh.homework.model.vo.Attachment;
import com.kh.homework.model.vo.Homework;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class insertController
 */
@WebServlet("/insert.homework")
public class insertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
		
			int maxSize = 10*1024*1024;
			String savePath = request.getSession().getServletContext().getRealPath("/resources/upfiles/homework/");
		
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
			String homeworkTitle = multiRequest.getParameter("title");
			String homeworkSubjectName = multiRequest.getParameter("subject");
			String homeworkWriter = multiRequest.getParameter("userNo");
		
			Homework hw = new Homework();
			hw.setHomeworkTitle(homeworkTitle);
			hw.setSubjectName(homeworkSubjectName);
			hw.setUserNo(homeworkWriter);
		
			Attachment at = null; // 처음에는 null로 초기화, 넘어온 첨부파일이 있다면 생성
			if(multiRequest.getOriginalFileName("upfile") != null) {
				
				at = new Attachment();
				at.setOriginName(multiRequest.getOriginalFileName("upfile"));
				at.setChangeName(multiRequest.getFilesystemName("upfile"));
				at.setFilePath("resources/upfiles/homework/");
				
			}
			
			int result = new HomeworkService().insertHomework(hw,at);
		
			if (result > 0) { 
//				request.getSession().setAttribute("alertMsg", "게시글 등록 성공");
				response.sendRedirect(request.getContextPath()+"homework/?cpage=1");
			}
		
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
