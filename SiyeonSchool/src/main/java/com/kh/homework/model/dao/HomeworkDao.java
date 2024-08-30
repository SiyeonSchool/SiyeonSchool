package com.kh.homework.model.dao;
import static com.kh.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.homework.model.vo.Homework;

public class HomeworkDao {

	private Properties prop = new Properties();
	
	public HomeworkDao() {
		
		try {
			prop.loadFromXML(new FileInputStream(HomeworkDao.class.getResource("db/sql/homework-mapper.xml").getPath()));
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
	
	
}
