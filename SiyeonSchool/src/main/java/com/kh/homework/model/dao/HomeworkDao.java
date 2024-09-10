package com.kh.homework.model.dao;
import static com.kh.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.homework.model.vo.Homework;
import com.kh.homework.model.vo.Subject;
import com.kh.user.model.vo.Question;

public class HomeworkDao {

	private Properties prop = new Properties();
	
	public HomeworkDao() {
		
		try {
			prop.loadFromXML(new FileInputStream(HomeworkDao.class.getResource("/db/sql/homework-mapper.xml").getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertHomework(Connection conn, Homework hw) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertHomework");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public int addSubject(Connection conn, String subjectName) {
        int result = 0;
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("addSubject");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, subjectName);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }

        return result;
    }
	
	
	public ArrayList<Subject> selectSubject(Connection conn) {
	    ArrayList<Subject> list = new ArrayList<>();
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    String sql = prop.getProperty("selectSubject");

	    try {
	        pstmt = conn.prepareStatement(sql);
	        rset = pstmt.executeQuery();

	        while (rset.next()) {
	        	Subject s = new Subject();
	            s.setSubjectNo(rset.getString("SUBJECT_NO"));
	            s.setSubjectName(rset.getString("SUBJECT_NAME"));
	            list.add(s);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close(rset);
	        close(pstmt);
	    }

	    return list;
	}
	
	
}
