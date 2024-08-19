package com.kh.user.model.service;

import java.sql.Connection;

import static com.kh.common.JDBCTemplate.*;

import com.kh.user.model.dao.UserDao;
import com.kh.user.model.vo.User;

public class UserService {

    public User loginUser(String userId, String userPwd){

        Connection conn = getConnection();

        User user = new UserDao().loginUser(conn, userId, userPwd);

        close(conn);

        return user;

    }

    public int checkId(String checkId) {
    	
		Connection conn = getConnection();
		
		int count = new UserDao().checkId(conn, checkId);
		
		close(conn);
		
		return count;
	}
    
    public int insertUser(User u) {
		Connection conn = getConnection();
		int result = new UserDao().insertUser(conn, u);

		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}

		close(conn);
		return result;
	}
}
