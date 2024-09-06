package com.kh.myPage.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.myPage.model.dao.MyPageDao;
import com.kh.myPage.model.vo.Attendance;
import com.kh.user.model.vo.User;

import static com.kh.common.JDBCTemplate.*;

public class MyPageService {

    public int selectUser(String userId){
        Connection conn = getConnection();

        int result = new MyPageDao().selectUser(conn, userId);

        close(conn);

        return result;
    }

    public ArrayList<Attendance> selectAtd(int userNo, String currentMonth){
        Connection conn = getConnection();

        ArrayList<Attendance> atd = new MyPageDao().selectAtd(conn, userNo, currentMonth);

        close(conn);
        return atd;
    }

    public User updateMyInfo(User u){
        Connection conn = getConnection();
        int result = new MyPageDao().updateMyInfo(conn, u);

        User updateUser = null;
        
        if(result > 0) {
            commit(conn);
            updateUser = new MyPageDao().updateSelectUser(conn,u.getUserNo());
        }else {
            rollback(conn);
        }

        close(conn);

        return updateUser;
    }

}
