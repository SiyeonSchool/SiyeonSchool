package com.kh.classroom.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.classroom.model.vo.ClassPost;
import static com.kh.common.JDBCTemplate.*;
import com.kh.common.model.vo.PageInfo;
import com.kh.user.model.vo.User;

public class ClassroomDao {

	private Properties prop = new Properties();
	
	public ClassroomDao() {
		String filePath = User.class.getResource("/db/sql/classroom-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int selectAllListCount(Connection conn) {
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAllListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return listCount;
	}

	public ArrayList<ClassPost> selectAllList(Connection conn, PageInfo pi) {
		ArrayList<ClassPost> list = new ArrayList<ClassPost>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAllList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getcPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new ClassPost(rset.getString("POST_NO"),
									   rset.getString("BOARD_NAME"),
									   rset.getString("USER_NAME"),
									   rset.getString("USER_ID"),
									   rset.getString("PROFILE_PATH"),
									   rset.getString("POST_TITLE"),
									   rset.getString("CREATE_DATE"),
									   rset.getInt("COMMENT_COUNT"),
									   rset.getInt("FILE_COUNT")
									   ));
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
