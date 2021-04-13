package com.isoft.service;

import com.isoft.dao.MarketDao;
import com.isoft.db.SqlSessionUtil;
import com.isoft.entity.Consultation;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;

public class MarketService {
    private MarketDao marketDao;
    private SqlSession sqlSession;
    public MarketService() {
        this.sqlSession= SqlSessionUtil.getInstance(null).getSqlSession();
        marketDao=sqlSession.getMapper(MarketDao.class);
    }
    public int consultAddStudent(String cname,String cphone,String csex,String cage,String  cparents,String cintroduce,String consultaion_method,String cpurpose,String consultation_course,String cfollow_status,String creturn_visit,String communication_content,String consulting_school,String channel,String csaleman,String ctime){
        int rs=marketDao.consultAddStudent(cname,cphone,csex,cage,cparents,cintroduce,consultaion_method,cpurpose,consultation_course,cfollow_status,creturn_visit,communication_content,consulting_school,channel,csaleman,ctime);
        if(rs>0){
            sqlSession.commit();
        }
        return rs;
    }
    public int updataData(String cname,String cphone,String csex,String cage,String  cparents,String cintroduce,String consultaion_method,String cpurpose,String consultation_course,String cfollow_status,String creturn_visit,String communication_content,String consulting_school,String channel,String csaleman,int cid){
        int rs=marketDao.updataData(cname,cphone,csex,cage,cparents,cintroduce,consultaion_method,cpurpose,consultation_course,cfollow_status,creturn_visit,communication_content,consulting_school,channel,csaleman,cid);
        if(rs>0){
            sqlSession.commit();
        }
        return rs;
    }
    public int getConsultNum(){
        int num=marketDao.getConsultNum();
        return num;
    }
    public ArrayList<Consultation> getConsultSome(int pageStart, int pageEnd){
        ArrayList<Consultation> list=new ArrayList<>();
        list= marketDao.getConsultSome(pageStart,pageEnd);
        return list;
    }
    public Consultation getConsultStudent(int cid){
        Consultation consultation= marketDao.getConsultStudent(cid);
        return consultation;
    }
    public ArrayList<Consultation> searchConsult(String searchtext){
        ArrayList<Consultation> list=new ArrayList<>();
        list= marketDao.searchConsult(searchtext);
        return list;
    }
}
