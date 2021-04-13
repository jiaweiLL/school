package com.isoft.service;

import com.isoft.dao.NewsDao;
import com.isoft.db.SqlSessionUtil;
import com.isoft.entity.News;
import com.isoft.entity.Sign;
import com.isoft.entity.Sys;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.Date;

public class NewsService {
    private NewsDao newsDao;
    private SqlSession sqlSession;
    public NewsService() {
        this.sqlSession= SqlSessionUtil.getInstance(null).getSqlSession();
        newsDao=sqlSession.getMapper(NewsDao.class);
    }

    public int putNotice(int name_id,String name, String title, String text,String time){
        int rs=newsDao.putNotice(name_id,name,title,text,time);
        if(rs>0) {
            sqlSession.commit();
            return rs;
        }
        return 0;
    }
    public int getNewsNum(int name_id){
        int num=newsDao.getNewsNum(name_id);
        return num;
    }
    public ArrayList<News> getsome(int name_id, int pageStart, int pageEnd){
        ArrayList<News> list=new ArrayList<>();
        list= newsDao.getsome(name_id,pageStart,pageEnd);
        return list;
    }
    public ArrayList<News> getAlldynamic(String name){
        ArrayList<News> list=new ArrayList<>();
        list= newsDao.getAlldynamic(name);
        return list;
    }
    public int putSign(String name, String text,String sign_class,String time,int name_id,String starttime,String endtime){
        int rs=newsDao.putSign(name,text,sign_class,time,name_id,starttime,endtime);
        if(rs>0) {
            sqlSession.commit();
            return rs;
        }
        return 0;
    }
    public int getSignNum(int name_id){
        int num=newsDao.getSignNum(name_id);
        return num;
    }
    public ArrayList<News> getSign(int name_id,int pageStart,int pageEnd){
        ArrayList<News> list=new ArrayList<>();
        list= newsDao.getSign(name_id,pageStart,pageEnd);
        return list;
    }
    public int newsrubbish(int id){
        int rs=newsDao.newsrubbish(id);
        if(rs>0) {
            sqlSession.commit();
            return rs;
        }
        return 0;
    }
    public ArrayList<Sign> getcheckSign(int newsid){
        ArrayList<Sign> list=newsDao.getcheckSign(newsid);
        return list;
    }
    public News getnews(int newsid){
        News news=newsDao.getnews(newsid);
        return news;
    }
    public ArrayList<Sys> getNoSign(int teacher_id, String sign_class, int newsid){
        ArrayList<Sys> list=newsDao.getNoSign(teacher_id,sign_class,newsid);
        return list;
    }
    public Sign checkSign(int newsid,int name_id){
        Sign sign =newsDao.checkSign(newsid,name_id);
        return sign;
    }
    public int forSign(int signid,int name_id,String name,String time,int teacher_id,String teacher,String school,String class_name){
        int rs=newsDao.forSign(signid,name_id,name,time,teacher_id,teacher,school,class_name);
        if(rs>0) {
            sqlSession.commit();
            return rs;
        }
        return 0;
    }







    public int getStuSignNum(int name_id,String sign_class){
        int num=newsDao.getStuSignNum(name_id,sign_class);
        return num;
    }
    public int getTeadynamicNum(int user_id){
        int num=newsDao.getTeadynamicNum(user_id);
        return num;
    }
    public int getTeaSignNum(int user_id){
        int num=newsDao.getTeaSignNum(user_id);
        return num;
    }



    public ArrayList<News> getStuSign(int name_id,String sign_class,int pageStart,int pageEnd){
        ArrayList<News> list=new ArrayList<>();
        list= newsDao.getStuSign(name_id,sign_class,pageStart,pageEnd);
        return list;
    }
    public ArrayList<News> getTeadynamic(int user_id,int pageStart,int pageEnd){
        ArrayList<News> list=new ArrayList<>();
        list= newsDao.getTeadynamic(user_id,pageStart,pageEnd);
        return list;
    }
    public ArrayList<News> getTeaSign(int name_id,int pageStart,int pageEnd){
        ArrayList<News> list=new ArrayList<>();
        list= newsDao.getTeaSign(name_id,pageStart,pageEnd);
        return list;
    }


    public int deleteSign(int id){
        int rs=newsDao.deleteSign(id);
        if(rs>0) {
            sqlSession.commit();
            return rs;
        }
        return 0;
    }

    public int toSign(int newsid,int name_id,String name,String time,int head_id,String head_name,String school,String class_name){
        int rs=newsDao.toSign(newsid,name_id,name,time,head_id,head_name,school,class_name);
        if(rs>0) {
            sqlSession.commit();
            return rs;
        }
        return 0;
    }










    public int deleteSigned(int id){
        int rs=newsDao.deleteSigned(id);
        sqlSession.commit();
        return rs;
    }

    public ArrayList<Sign> getPersonAllSign(int name_id,int pageStart,int pageEnd){
        ArrayList<Sign> list=new ArrayList<>();
        list= newsDao.getPersonAllSign(name_id,pageStart,pageEnd);
        return list;
    }
    public int getPersonSignNum(int name_id){
        int rs=newsDao.getPersonSignNum(name_id);
        return rs;
    }

    public ArrayList<Sys> getTeacherNoSign(int teacher_id,int signid){
        ArrayList<Sys> list=newsDao.getTeacherNoSign(teacher_id,signid);
        return list;
    }
}
