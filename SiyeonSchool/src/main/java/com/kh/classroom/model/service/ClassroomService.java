package com.kh.classroom.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.classroom.model.dao.ClassroomDao;
import com.kh.classroom.model.vo.ClassPost;
import com.kh.common.model.vo.PageInfo;

import static com.kh.common.JDBCTemplate.*;

public class ClassroomService {

	public int selectAllListCount() {
		Connection conn = getConnection();
		int result = new ClassroomDao().selectAllListCount(conn);
		close(conn);
		return result;
	}

	public ArrayList<ClassPost> selectAllList(PageInfo pi) {
		Connection conn = getConnection();
		ArrayList<ClassPost> list = new ClassroomDao().selectAllList(conn, pi);
		close(conn);
		return list;
	}

	
	
}
