package com.isoft.service;

import com.isoft.dao.SysDao;
import com.isoft.db.SqlSessionUtil;
import com.isoft.entity.Sys;
import com.isoft.util.StringUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;

public class SysService {
    private SysDao sysDao ;
    private SqlSession sqlSession;
    public SysService() {
        this.sqlSession= SqlSessionUtil.getInstance(null).getSqlSession();
        sysDao=sqlSession.getMapper(SysDao.class);
    }
    public Sys loginCheck(String sno , String password) {
        if(StringUtil.isEmpty(sno) || StringUtil.isEmpty(password)) {
            return null ;
        }
        Sys sys = sysDao.logincheck(sno , password) ;
        return sys ;
    }
    public Sys CheckSno(String sno) {
        Sys sys = sysDao.checkSno(sno);
        return sys ;
    }
    public int addstudent(String name,String sno,String password,String sex,String age,String address,String parents,String phone,String curriculum,int class_hour,String head,int head_id,int renew,String class_name,String school,String schoolmaster,int schoolmaster_id,String level,String time){
        int rs=sysDao.addstudent(name,sno,password,sex,age,address,parents,phone,curriculum,class_hour,head,head_id,renew,class_name,school,schoolmaster,schoolmaster_id,level,time);
        if(rs>0) {
            sqlSession.commit();
            return rs;
        }
        return 0;
    }
    public ArrayList<Sys> getAllStudent(int head_id){
        ArrayList<Sys> list=new ArrayList<>();
        list= sysDao.getAllStudent(head_id);
        return list;
    }
    public ArrayList<Sys> getAllschool(){
        ArrayList<Sys> list=new ArrayList<>();
        list= sysDao.getAllschool();
        return list;
    }
    public ArrayList<Sys> search(int head_id ,String searchtext){
        ArrayList<Sys> list=new ArrayList<>();
        list= sysDao.search(head_id,searchtext);
        return list;
    }

    public Sys getStudentDate(int id) {
        Sys sys = sysDao.getStudentDate(id);
        return sys ;
    }

    public int deleteDate(int id){
        int rs=sysDao.deleteDate(id);
        if(rs>0) {
            sqlSession.commit();
            return rs;
        }
        return 0;
    }
    public int userrubbish(int id){
        int rs=sysDao.userrubbish(id);
        if(rs>0) {
            sqlSession.commit();
            return rs;
        }
        return 0;
    }
    public int updataDate(String name,String password,String sno,String sex,String age,String address,int id,String level){
        int rs=sysDao.updateData(name,password,sno,sex,age,address,id,level);
        if(rs>0) {
            sqlSession.commit();
            return rs;
        }
        return 0;
    }

    public Sys getStudent_teacher(int id,int teacher_id) {
        Sys sys = sysDao.getStudent_teacher(id,teacher_id);
        return sys ;
    }

    public int updata_hour(int class_hour,int id,int teacher_id){
        int rs=sysDao.updata_hour(class_hour,id,teacher_id);
        if(rs>0) {
            sqlSession.commit();
            return rs;
        }
        return 0;
    }
    public int updataTeacherDate(String name,String password,String sno,String sex,String age,String address,String phone,String curriculum,int id,String teacher_level){
        int rs=sysDao.updateTeacherData(name,password,sno,sex,age,address,phone,curriculum,id,teacher_level);
        if(rs>0) {
            sqlSession.commit();
            return rs;
        }
        return 0;
    }
    public int updataSchool(String name,String password,String sno,String sex,String age,String address,String phone,String school,int id){
        int rs=sysDao.updataSchool(name,password,sno,sex,age,address,phone,school,id);
        if(rs>0) {
            sqlSession.commit();
            return rs;
        }
        return 0;
    }
    public int schupdateStudentData(String name,String password,String sno,String sex,String age,String address,String parents,String phone,String curriculum,int class_hour,int id,String class_name,String level){
        int rs=sysDao.BupdteStudentData(name,password,sno,sex,age,address,parents,phone,curriculum,class_hour,id,class_name,level);
        if(rs>0) {
            sqlSession.commit();
            return rs;
        }
        return 0;
    }
    public int addTeacher(String name,String sno,String password,String sex,String age,String address,String phone,String curriculum,String head,int head_id,String school,String time,String teacher_level){
        int rs=sysDao.addTeacher(name,password,sno,sex,age,address,phone,curriculum,head,head_id,school,time,teacher_level);
        if(rs>0) {
            sqlSession.commit();
            return rs;
        }
        return 0;
    }
    public ArrayList<Sys> searchStudent(String searchtext){
        ArrayList<Sys> list=new ArrayList<>();
        list= sysDao.searchStudent(searchtext);
        return list;
    }

    public int moveStudentData(String name,int teacher_id,String teacher,String curriculum,int class_hour,int id){
        int rs=sysDao.moveStudentData(name,teacher_id,teacher,curriculum,class_hour,id);
        if(rs>0) {
            sqlSession.commit();
            return rs;
        }
        return 0;
    }
    public Sys selectMoveTeacher(String teacher) {
        Sys sys = sysDao.selectMoveTeacher(teacher);
        return sys ;
    }
}
