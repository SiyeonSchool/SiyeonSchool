package com.kh.mail.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

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
import com.kh.mail.model.vo.MailReceiver;
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
			int maxSize = 20*1024*1024; // 20MB
			String savePath = request.getSession().getServletContext().getRealPath("/resources/upfiles/mail/");
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
			// form 데이터 받아오기
			int senderNo = ((User)(request.getSession().getAttribute("loginUser"))).getUserNo();
			String title = multiRequest.getParameter("title");
			String content = multiRequest.getParameter("content");
			String[] userNoList = multiRequest.getParameterValues("userNo");		
			String[] rTypeList = multiRequest.getParameterValues("rType");
			String isSent = multiRequest.getParameter("isSent");
			
			// 메일 데이터
			Mail m = new Mail();
			m.setUserNo(senderNo);
			m.setMailTitle(title);
			m.setMailContent(content);
			m.setIsSent(isSent);
			
			// 첨부파일 데이터
			Attachment at = null;
			if(multiRequest.getOriginalFileName("upfile") != null) { // 새로 넘어온 첨부파일이 있으면 생성
				at = new Attachment();
				at.setOriginName(multiRequest.getOriginalFileName("upfile"));
				at.setChangeName(multiRequest.getFilesystemName("upfile"));
				at.setFilePath("resources/upfiles/mail/");
			}else if(multiRequest.getParameter("originFileName") != null) {
				at = new Attachment();
				String originFileName = multiRequest.getParameter("originFileName"); // 이전 원본메일 첨부파일명
				at.setOriginName(originFileName);
				at.setChangeName(getCustomChangeName(originFileName));
				at.setFilePath("resources/upfiles/mail/");
			}
			
			// 수신인 데이터
			ArrayList<MailReceiver> mrList = new ArrayList<MailReceiver>();
			if(userNoList != null) { // 수신인 데이터가 있을때만 (임시저장시에는 수신인이 없을수도 있음)
				for(int i=0; i<userNoList.length; i++) {
					MailReceiver mr = new MailReceiver();
					mr.setReceiverNo(Integer.parseInt(userNoList[i]));
					mr.setReceiverType(rTypeList[i].toUpperCase());
					mrList.add(mr);
				}
			}
			
			// 로그인사용자번호
			int loginUserNo = ((User)(request.getSession().getAttribute("loginUser"))).getUserNo();
			
			int result = new MailService().insertMail(m, at, mrList, loginUserNo);
			
			if(result > 0) { // 성공
//				if(m.getIsSent().equals("T")) { // 임시저장메일인경우
//					request.getSession().setAttribute("mailAlertMsg", "메일을 임시저장하였습니다.");
//				}else {
//					request.getSession().setAttribute("mailAlertMsg", "성공적으로 메일을 전송하였습니다.");
//				}
				
			} else { // 실패
				if(at != null) { // 첨부파일이 있는경우
					new File(savePath + at.getChangeName()).delete(); // 첨부파일 삭제
				}
				request.getSession().setAttribute("mailAlertMsg", "메일 전송 실패");
			}
			
			if(m.getIsSent().equals("T")) { // 임시저장메일인경우
				response.sendRedirect(request.getContextPath() + "/mail?mb=t&cpage=1"); // 임시보관함으로
			}else {
				response.sendRedirect(request.getContextPath() + "/mail?mb=s&cpage=1"); // 보낸메일함으로
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	// 파일명 문자열로 받아서 새로운 파일명 반환하는 메소드
	private String getCustomChangeName(String originName) {
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); 
		int ranNum = (int)(Math.random() * 90000 + 10000); 
		String ext = originName.substring(originName.lastIndexOf(".")); 
		String changeName = currentTime + ranNum + ext;
		return changeName;
	}
}
