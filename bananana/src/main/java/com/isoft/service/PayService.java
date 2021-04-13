package com.isoft.service;

import com.isoft.dao.NewsDao;
import com.isoft.dao.PayDao;
import com.isoft.db.SqlSessionUtil;
import com.isoft.entity.Sys;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;

public class PayService {
    private PayDao payDao;
    private SqlSession sqlSession;
    public PayService() {
        this.sqlSession= SqlSessionUtil.getInstance(null).getSqlSession();
        payDao=sqlSession.getMapper(PayDao.class);
    }
    public int payupdateStudent(int name_id,String name,int class_hour,String money,int head_id,String head_name,String class_name,String school,int schoolmaster_id,String schoolmaster,String time){
        int rs=payDao.payupdateStudent(name_id,name,class_hour,money,head_id,head_name,class_name,school,schoolmaster_id,schoolmaster,time);
        if(rs>0) {
            sqlSession.commit();
            return rs;
        }
        return 0;
    }
    public int updata_totalpay(int class_hour,int total_pay,int renew,int id,int teacher_id){
        int rs=payDao.updata_totalpay(class_hour,total_pay,renew,id,teacher_id);
        if(rs>0) {
            sqlSession.commit();
            return rs;
        }
        return 0;
    }
    public ArrayList<Sys> schoolmasterSearch(int schoolmaster_id , String searchtext){
        ArrayList<Sys> list=new ArrayList<>();
        list= payDao.schoolmasterSearch(schoolmaster_id,searchtext);
        return list;
    }
}
