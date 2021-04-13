package com.isoft.service;

import com.isoft.dao.MoneyDao;
import com.isoft.db.SqlSessionUtil;
import com.isoft.entity.Apply;
import com.isoft.entity.Consultation;
import com.isoft.entity.Entry;
import com.isoft.entity.Storehouse;
import org.apache.ibatis.session.SqlSession;

import javax.mail.Store;
import java.util.ArrayList;

public class MoneyService {
    private MoneyDao moneyDao;
    private SqlSession sqlSession;
    public MoneyService() {
        this.sqlSession= SqlSessionUtil.getInstance(null).getSqlSession();
        moneyDao=sqlSession.getMapper(MoneyDao.class);
    }
    public int addStore(String sproduct_Name,String sbrand,String specifications,String scategory,String sbuy_price,String sguide_price,String sunit,String snumber,String school,String stime){
        int rs=moneyDao.addStore(sproduct_Name,sbrand,specifications,scategory,sbuy_price,sguide_price,sunit,snumber,school,stime);
        if(rs>0){
            sqlSession.commit();
        }
        return rs;
    }
    public int getStoreNum(){
        int num=moneyDao.getStoreNum();
        return num;
    }
    public int getSchoolStoreNum(String school){
        int num=moneyDao.getSchoolStoreNum(school);
        return num;
    }
    public ArrayList<Storehouse> getStoreSome(int pageStart, int pageEnd){
        ArrayList<Storehouse> list=new ArrayList<>();
        list= moneyDao.getStore(pageStart,pageEnd);
        return list;
    }
    public ArrayList<Storehouse> getSchoolStoreSome(int pageStart, int pageEnd,String school){
        ArrayList<Storehouse> list=new ArrayList<>();
        list= moneyDao.getSchoolStore(pageStart,pageEnd,school);
        return list;
    }
    public Storehouse getStoredata(int sid){
        Storehouse storehouse= moneyDao.getStoredata(sid);
        return storehouse;
    }
    public ArrayList<Storehouse> searchStore(String searchtext){
        ArrayList<Storehouse> list=new ArrayList<>();
        list= moneyDao.searchStore(searchtext);
        return list;
    }
    public ArrayList<Storehouse> searchSchoolStore(String searchtext,String school){
        ArrayList<Storehouse> list=new ArrayList<>();
        list= moneyDao.searchSchoolStore(searchtext,school);
        return list;
    }


    public int addApply(String aproduct_name,String abrand,String aspecifications,String acategory,String aunit,String anumber,String abuy_price,String aprice,String adepartment,String aname,String ause,String atime){
        int rs=moneyDao.addApply(aproduct_name,abrand,aspecifications,acategory,aunit,anumber,abuy_price,aprice,adepartment,aname,ause,atime);
        if(rs>0){
            sqlSession.commit();
        }
        return rs;
    }
    public int getApplyNum(){
        int num=moneyDao.getApplyNum();
        return num;
    }
    public int getSchoolApplyNum(String adepartment){
        int num=moneyDao.getSchoolApplyNum(adepartment);
        return num;
    }
    public ArrayList<Apply> getApplySome(int pageStart, int pageEnd){
        ArrayList<Apply> list=new ArrayList<>();
        list= moneyDao.getApply(pageStart,pageEnd);
        return list;
    }
    public ArrayList<Apply> getSchoolApplySome(int pageStart, int pageEnd,String adepartment){
        ArrayList<Apply> list=new ArrayList<>();
        list= moneyDao.getSchoolApply(pageStart,pageEnd,adepartment);
        return list;
    }
    public Apply getApplydata(int aid){
        Apply apply= moneyDao.getApplydata(aid);
        return apply;
    }
    public ArrayList<Apply> searchApply(String searchtext){
        ArrayList<Apply> list=new ArrayList<>();
        list= moneyDao.searchApply(searchtext);
        return list;
    }
    public ArrayList<Apply> searchSchoolApply(String searchtext,String adepartment){
        ArrayList<Apply> list=new ArrayList<>();
        list= moneyDao.searchSchoolApply(searchtext,adepartment);
        return list;
    }
    public int updateNumber(String snumber,int sid){
        int rs=moneyDao.updateNumber(snumber,sid);
        if(rs>0){
            sqlSession.commit();
        }
        return rs;
    }
    public ArrayList<Apply> exportApply(String adepartment,String starttime,String endtime){
        ArrayList<Apply> list=new ArrayList<>();
        list= moneyDao.exportApply(adepartment,starttime,endtime);
        return list;
    }
    public ArrayList<Storehouse> exportStore(String school,String starttime,String endtime){
        ArrayList<Storehouse> list=new ArrayList<>();
        list= moneyDao.exportStore(school,starttime,endtime);
        return list;
    }
    public ArrayList<Entry> exportEntry(String edepartment,String starttime,String endtime){
        ArrayList<Entry> list=new ArrayList<>();
        list= moneyDao.exportEntry(edepartment,starttime,endtime);
        return list;
    }






    public int addEntry(String eremit_account,String eremit_money,String eremit_note,String estoreman,String eleadman,String edepartment,String etime){
        int rs=moneyDao.addEntry(eremit_account,eremit_money,eremit_note,estoreman,eleadman,edepartment,etime);
        if(rs>0){
            sqlSession.commit();
        }
        return rs;
    }

    public int getEntryNum(){
        int num=moneyDao.getEntryNum();
        return num;
    }
    public int getSchoolEntryNum(String edepartment){
        int num=moneyDao.getSchoolEntryNum(edepartment);
        return num;
    }
    public ArrayList<Entry> getEntrySome(int pageStart, int pageEnd){
        ArrayList<Entry> list=new ArrayList<>();
        list= moneyDao.getEntry(pageStart,pageEnd);
        return list;
    }
    public ArrayList<Entry> getSchoolEntrySome(int pageStart, int pageEnd,String edepartment){
        ArrayList<Entry> list=new ArrayList<>();
        list= moneyDao.getSchoolEntry(pageStart,pageEnd,edepartment);
        return list;
    }
    public Entry getEntrydata(int eid){
        Entry entry= moneyDao.getEntrydata(eid);
        return entry;
    }
    public ArrayList<Entry> searchEntry(String searchtext){
        ArrayList<Entry> list=new ArrayList<>();
        list= moneyDao.searchEntry(searchtext);
        return list;
    }
    public ArrayList<Entry> searchSchoolEntry(String searchtext,String edepartment){
        ArrayList<Entry> list=new ArrayList<>();
        list= moneyDao.searchSchoolEntry(searchtext,edepartment);
        return list;
    }
}
