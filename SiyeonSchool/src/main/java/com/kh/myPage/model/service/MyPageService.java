package com.kh.myPage.model.service;

import java.sql.Connection;

import com.kh.myPage.model.dao.MyPageDao;

import static com.kh.common.JDBCTemplate.*;

public class MyPageService {

    public int selectUser(String userId){
        Connection conn = getConnection();

        int result = new MyPageDao().selectUser(conn, userId);

        close(conn);

        return result;
    }

}
