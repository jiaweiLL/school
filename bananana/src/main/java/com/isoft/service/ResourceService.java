package com.isoft.service;

import com.isoft.dao.PersonnelDao;
import com.isoft.dao.ResourceDao;
import com.isoft.db.SqlSessionUtil;
import com.isoft.entity.Resource;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;

public class ResourceService {
    private ResourceDao resourceDao;
    private SqlSession sqlSession;
    public ResourceService() {
        this.sqlSession= SqlSessionUtil.getInstance(null).getSqlSession();
        resourceDao=sqlSession.getMapper(ResourceDao.class);
    }
    public int updateRollPicture(String url,int putid,String putname,String time,String title){
        int rs=resourceDao.updateRollPicture(url,putid,putname,time,title);
        if(rs>0) {
            sqlSession.commit();
            return rs;
        }
        return 0;
    }
    public int addNoticeVideo(String url,String title,String text,String content,int putid,String putname,String time){
        int rs=resourceDao.addNoticeVideo(url,title,text,content,putid,putname,time);
        if(rs>0) {
            sqlSession.commit();
            return rs;
        }
        return 0;
    }
    public int addNotice(String url,String title,String content,int putid,String putname,String time){
        int rs=resourceDao.addNotice(url,title,content,putid,putname,time);
        if(rs>0) {
            sqlSession.commit();
            return rs;
        }
        return 0;
    }
    public int addDance(String url,String title,String text,String content,String coursetype,int putid,String putname,String time){
        int rs=resourceDao.addDance(url,title,text,content,coursetype,putid,putname,time);
        if(rs>0) {
            sqlSession.commit();
            return rs;
        }
        return 0;
    }
    public ArrayList<Resource> getRollPicture(){
        ArrayList<Resource> resources=new ArrayList<>();
        resources=resourceDao.getRollPicture();
        return resources;
    }
    public int getNoticeVideoNum(){
        int num=resourceDao.getNoticeVideoNum();
        return num;
    }
    public ArrayList<Resource> getDanceSome(int pageStart, int pageEnd,String coursetype){
        ArrayList<Resource> list=new ArrayList<>();
        list= resourceDao.getDanceSome(pageStart,pageEnd,coursetype);
        return list;
    }
    public int getDanceNum(String coursetype){
        int num=resourceDao.getDanceNum(coursetype);
        return num;
    }
    public ArrayList<Resource> getNoticeVideoSome(int pageStart, int pageEnd){
        ArrayList<Resource> list=new ArrayList<>();
        list= resourceDao.getNoticeVideoSome(pageStart,pageEnd);
        return list;
    }
    public int getNoticeNum(){
        int num=resourceDao.getNoticeNum();
        return num;
    }
    public ArrayList<Resource> getNoticeSome(int pageStart, int pageEnd){
        ArrayList<Resource> list=new ArrayList<>();
        list= resourceDao.getNoticeSome(pageStart,pageEnd);
        return list;
    }
    public Resource getNoticeData(int id){
        Resource resource=resourceDao.getNoticeData(id);
        return resource;
    }
    public Resource getDanceData(int id){
        Resource resource=resourceDao.getDanceData(id);
        return resource;
    }
}
