package com.isoft.service;

import com.isoft.dao.PersonnelDao;
import com.isoft.dao.SysDao;
import com.isoft.db.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class PersonnelSerivice {
    private PersonnelDao personnelDao ;
    private SqlSession sqlSession;
    public PersonnelSerivice() {
        this.sqlSession= SqlSessionUtil.getInstance(null).getSqlSession();
        personnelDao=sqlSession.getMapper(PersonnelDao.class);
    }
    public int addPeople(String name,String sno,String password,String sex,String age,String address,String phone,String school,String time,String power,String job){
        int rs=personnelDao.addPeople(name,password,sno,sex,age,address,phone,school,time,power,job);
        if(rs>0) {
            sqlSession.commit();
            return rs;
        }
        return 0;
    }
}
