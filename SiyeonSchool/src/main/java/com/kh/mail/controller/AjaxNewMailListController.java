package com.kh.mail.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.kh.mail.model.service.MailService;
import com.kh.mail.model.vo.Mail;
import com.kh.user.model.vo.User;

/**
 * Servlet implementation class AjaxNewMailListController
 */
@WebServlet("/mail.list.new")
public class AjaxNewMailListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxNewMailListController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 새메일리스트 조회 컨트롤러: 지금으로부터 몇초 전부터 받은메일들 (mail-mapper.xml 참고)
		
		HttpSession session = request.getSession();
		int loginUserNo = ((User)(session.getAttribute("loginUser"))).getUserNo();
		
		// DB에서 조회
		ArrayList<Mail> newMailListFromDB = new MailService().selectNewMailList(loginUserNo);
		
		// 세션에 보관하고 있는 기존 메일번호 리스트
		ArrayList<String> trackingNewMailNoList = (ArrayList<String>)session.getAttribute("trackingNewMailNoList"); 
		if(trackingNewMailNoList == null) {
			trackingNewMailNoList = new ArrayList<String>();
		}
		
		// jsp로 보낼 메일객체 리스트 생성
		ArrayList<Mail> tempList = new ArrayList<Mail>();
		
		if(newMailListFromDB.size() > 0) {
			for (Mail m : newMailListFromDB) { // 새로 db에서 조회한 리스트를 순회하면서,
				String mailNo = m.getMailNo();
				if(!trackingNewMailNoList.contains(mailNo)) { // 세션에 보관하고 있던 메일번호리스트에 해당 메일번호가 없으면,
					trackingNewMailNoList.add(mailNo); // 기존 메일번호리스트에 메일번호 추가
					tempList.add(m); // jsp로 보낼 리스트에도 추가
				}
			}  
		}
		
		session.setAttribute("trackingNewMailNoList", trackingNewMailNoList);
		
		response.setContentType("application/json; charset=utf-8");
		System.out.println(tempList);
		new Gson().toJson(tempList, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
