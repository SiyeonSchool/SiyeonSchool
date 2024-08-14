package com.kh.contacts.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;

import com.kh.contacts.model.dao.ContactsDao;
import com.kh.contacts.model.vo.Contacts;
import com.kh.contacts.model.vo.ContactsCategory;
import com.kh.user.model.vo.User;

public class ContactsService {
	
	public ArrayList<ContactsCategory> selectCategoryList() {
		Connection conn = getConnection();
		ArrayList<ContactsCategory> list = new ContactsDao().selectCategoryList(conn);
		close(conn);
		return list;
	}
	
	public ArrayList<Contacts> selectPrivateContactsList(int ownerNo) {
		Connection conn = getConnection();
		ArrayList<Contacts> list = new ContactsDao().selectPrivateContactsList(conn, ownerNo);
		close(conn);
		return list;
	}
	
	public ArrayList<Contacts> selectPublicContactsList(int categoryNo) {
		Connection conn = getConnection();
		ArrayList<Contacts> list = new ContactsDao().selectPublicContactsList(conn, categoryNo);
		close(conn);
		return list;
	}
	
	public ArrayList<User> selectUserList() {
		Connection conn = getConnection();
		ArrayList<User> list = new ContactsDao().selectUserList(conn);
		close(conn);
		return list;
	}


}
