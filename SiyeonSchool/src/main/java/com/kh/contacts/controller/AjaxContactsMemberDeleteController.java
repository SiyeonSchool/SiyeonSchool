package com.kh.contacts.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.kh.contacts.model.service.ContactsService;
import com.kh.contacts.model.vo.ContactsMember;

/**
 * Servlet implementation class AjaxContactsMemberDeleteController
 */
@WebServlet("/contacts/delete.member")
public class AjaxContactsMemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxContactsMemberDeleteController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String checkedUsersObjListStr = request.getParameter("checkedUsersObjList");
		ArrayList<ContactsMember> contactsMemberList = null;
		
		try {
			// 파싱할 리스트의 타입을 정의 (Define the type of the list to be parsed)
            Type userListType = new TypeToken<List<ContactsMember>>(){}.getType();

            // JSON string을 List<ContactsMember>형식으로 파싱
            contactsMemberList = new Gson().fromJson(checkedUsersObjListStr, userListType);

		} catch (Exception e) {
		    e.printStackTrace();
		}

		int result = new ContactsService().deleteContactsMember(contactsMemberList);
		
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
