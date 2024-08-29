package com.kh.myPage.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.myPage.model.dao.MyPageDao;
import com.kh.myPage.model.vo.Attendance;

import static com.kh.common.JDBCTemplate.*;

public class MyPageService {

    public int selectUser(String userId){
        Connection conn = getConnection();

        int result = new MyPageDao().selectUser(conn, userId);

        close(conn);

        return result;
    }

    public ArrayList<Attendance> selectAtd(int userNo){
        Connection conn = getConnection();

        ArrayList<Attendance> list = new MyPageDao().selectAtd(conn, userNo);

        close(conn);
        return list;
    }

}
