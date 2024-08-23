package com.kh.home.model.service;

import static com.kh.common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.ArrayList;

import com.kh.home.model.dao.HomeDao;
import com.kh.home.model.vo.Curriculum;

public class HomeService {
    public ArrayList<Curriculum> selectCurriculum(){
        Connection conn = getConnection();

        ArrayList<Curriculum> list = new HomeDao().selectCurriculum(conn);
        
        close(conn);
        
        return list;
    }

    public  ArrayList<Curriculum> updateCbState(String subject){
        Connection conn = getConnection();

        int result = new HomeDao().updateCbState(conn, subject);

        ArrayList<Curriculum> list = new ArrayList<Curriculum>();

        if(result > 0){
            commit(conn);
            list = new HomeDao().selectCurriculum(conn);
        }else{
            rollback(conn);
        }

        close(conn);

        return list;
    }

    public int getCompletedCount(){
        Connection conn = getConnection();
        int result = new HomeDao().getCompletedCount(conn);

        close(conn);

        return result;
    }

    public int getTotalCount(){
        Connection conn = getConnection();
        int result = new HomeDao().getTotalCount(conn);

        close(conn);

        return result;
    }
}
