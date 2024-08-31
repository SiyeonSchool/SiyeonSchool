package com.kh.mail.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.kh.common.MyFileRenamePolicy;
import com.kh.common.model.vo.Attachment;
import com.kh.mail.model.service.MailService;
import com.kh.mail.model.vo.Mail;
import com.kh.user.model.vo.User;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class MailInsertController
 */
@WebServlet("/mail.insert")
public class MailInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailInsertController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 메일보내기 컨트롤러
		
		request.setCharacterEncoding("utf-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			// 첨부파일 관련
			int maxSize = 10*1024*1024; // 10MB
			String savePath = request.getSession().getServletContext().getRealPath("/resources/upfiles/mail/");
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
			// form 데이터 받아오기
			int senderNo = ((User)(request.getSession().getAttribute("loginUser"))).getUserNo();
			String title = multiRequest.getParameter("title");
			String content = multiRequest.getParameter("content");
			
			System.out.println("title:" + title);
			System.out.println("content:" + content);
			
			// 메일 데이터
			Mail m = new Mail();
			m.setUserNo(senderNo);
			m.setMailTitle(title);
			m.setMailContent(content);
			m.setIsSent("S");
			
			// 첨부파일 데이터
			Attachment at = null;
			if(multiRequest.getOriginalFileName("upfile") != null) {
				at = new Attachment();
				at.setOriginName(multiRequest.getOriginalFileName("upfile"));
				at.setChangeName(multiRequest.getFilesystemName("upfile"));
				at.setFilePath("resources/upfiles/mail/");
			}
		
			System.out.println("---------------------");
			System.out.println("insert컨트롤러");
			System.out.println(m);
			System.out.println(at);
			System.out.println("---------------------");
			
			int result = new MailService().insertMail(m, at);
			
			if(result > 0) { // 성공
				request.getSession().setAttribute("mailAlertMsg", "성공적으로 메일을 전송하였습니다.");
			} else { // 실패
				if(at != null) { // 첨부파일이 있는경우
					new File(savePath + at.getChangeName()).delete(); // 첨부파일 삭제
				}
				request.getSession().setAttribute("mailAlertMsg", "메일 전송 실패");
			}
			
			response.sendRedirect(request.getContextPath() + "/mail?mb=s&cpage=1");
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
