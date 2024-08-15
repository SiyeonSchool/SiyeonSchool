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

}
