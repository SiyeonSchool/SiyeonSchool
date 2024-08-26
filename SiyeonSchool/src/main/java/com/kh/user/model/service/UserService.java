package com.kh.user.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;

import com.kh.user.model.dao.UserDao;
import com.kh.user.model.vo.Question;
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
    
    public ArrayList<Question> selectQuestions() {
        Connection conn = getConnection();
        ArrayList<Question> list = new UserDao().selectQuestions(conn);
        close(conn);
        return list;
    }
    
    public User findUser(String userName, String birthday, int questionNo, String questionAnswer) {
    	Connection conn = getConnection();
    	User user = new UserDao().findUser(conn, userName, birthday, questionNo, questionAnswer);
    	close(conn);
    	return user;
    }
    
    public int updateUserPwd(String userId, String updatePwd) {
    	Connection conn = getConnection();
    	int result = new UserDao().updateUserPwd(conn, updatePwd, userId);
    	
    	if(result > 0) {
    		commit(conn);
    	}else {
    		rollback(conn);
    	}
    	close(conn);
    	return result;
    }
    
}
