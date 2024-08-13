package com.kh.contacts.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;

import com.kh.contacts.model.dao.ContactsDao;
import com.kh.user.model.vo.User;

public class ContactsService {

	public ArrayList<User> selectUserList() {
		Connection conn = getConnection();
		ArrayList<User> list = new ContactsDao().selectUserList(conn);
		close(conn);
		return list;
	}

}
