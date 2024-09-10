package com.kh.homework.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.homework.model.dao.HomeworkDao;
import com.kh.homework.model.vo.Subject;

import static com.kh.common.JDBCTemplate.*;

public class HomeworkService {

	
	public int addSubject(String subjectName) {
        Connection conn = getConnection();
        int result = new HomeworkDao().addSubject(conn, subjectName);

        if (result > 0) {
            commit(conn);
        } else {
            rollback(conn);
        }

        close(conn);
        return result;
    }
	
	public ArrayList<Subject> selectSubject() {
        Connection conn = getConnection();
        ArrayList<Subject> list = new HomeworkDao().selectSubject(conn);
        close(conn);
        return list;
    }
	
}
