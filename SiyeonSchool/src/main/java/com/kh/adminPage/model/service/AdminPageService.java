package com.kh.adminPage.model.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.adminPage.model.dao.AdminPageDao;
import com.kh.user.model.vo.User;	

public class AdminPageService {

    public ArrayList<User> selectStudentList(){
        Connection conn = getConnection();

        ArrayList<User> list = new AdminPageDao().selectStudentList(conn);
        
        close(conn);

        return list;
    }

}
