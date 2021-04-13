package com.isoft.dao;

import com.isoft.entity.Apply;
import com.isoft.entity.Consultation;
import com.isoft.entity.Entry;
import com.isoft.entity.Storehouse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

public interface MoneyDao {
    /**
     * 添加库存
     * @param sproduct_Name
     * @param sbrand
     * @param specifications
     * @param scategory
     * @param sbuy_price
     * @param sguide_price
     * @param sunit
     * @param snumber
     * @param stime
     * @return
     */
    @Insert("insert into storehouse(sproduct_Name,sbrand,specifications,scategory,sbuy_price,sguide_price,sunit,snumber,scollected_month,snext_month,school,belong,stime) values(#{sproduct_Name},#{sbrand},#{specifications},#{scategory},#{sbuy_price},#{sguide_price},#{sunit},#{snumber},'0','0',#{school},'0',#{stime})")
    int addStore(@Param("sproduct_Name") String sproduct_Name,@Param("sbrand") String sbrand, @Param("specifications") String specifications,@Param("scategory") String scategory,@Param("sbuy_price") String sbuy_price,@Param("sguide_price") String sguide_price,@Param("sunit") String sunit,@Param("snumber") String snumber,@Param("school")String school,@Param("stime") String stime);

    /**
     * 分页查询库存
     * @param pageStart
     * @param pageEnd
     * @return
     */
    @Select("select * from storehouse order by stime desc limit #{pageStart},#{pageEnd}")
    ArrayList<Storehouse> getStore(@Param("pageStart") int pageStart, @Param("pageEnd") int pageEnd);
    /**
     * school分页查询库存
     * @param pageStart
     * @param pageEnd
     * @return
     */
    @Select("select * from storehouse where school=#{school} order by stime desc limit #{pageStart},#{pageEnd}")
    ArrayList<Storehouse> getSchoolStore(@Param("pageStart") int pageStart, @Param("pageEnd") int pageEnd,@Param("school")String school);
    /**
     * 模糊搜索库存
     * @param
     * @param searchtext
     * @return
     */
    @Select("select * from storehouse where sproduct_Name like concat('%',#{searchtext},'%')")
    ArrayList<Storehouse> searchStore(@Param("searchtext") String searchtext);
    /**
     * 模糊搜索school库存
     * @param
     * @param searchtext
     * @return
     */
    @Select("select * from storehouse where school=#{school} and sproduct_Name like concat('%',#{searchtext},'%')")
    ArrayList<Storehouse> searchSchoolStore(@Param("searchtext") String searchtext,@Param("school")String school);
    /**
     * 库存的数量
     */

    @Select("select count(sid) from storehouse")
    int getStoreNum();
    /**
     * school库存的数量
     */

    @Select("select count(sid) from storehouse where school=#{school}")
    int getSchoolStoreNum(@Param("school")String school);
    /**
     *获得单个库存
     * @return
     */
    @Select("select * from storehouse where sid=#{sid}")
    Storehouse getStoredata(@Param("sid") int sid);

    /**
     * 增加申请信息
     * @param aproduct_name
     * @param abrand
     * @param aspecifications
     * @param acategory
     * @param aunit
     * @param anumber
     * @param abuy_price
     * @param aprice
     * @param adepartment
     * @param aname
     * @param ause
     * @param time
     * @return
     */
    @Insert("insert into apply(aproduct_name,abrand,aspecifications,acategory,aunit,anumber,abuy_price,aprice,adepartment,aname,ause,atime) values(#{aproduct_name},#{abrand},#{aspecifications},#{acategory},#{aunit},#{anumber},#{abuy_price},#{aprice},#{adepartment},#{aname},#{ause},#{atime})")
    int addApply(@Param("aproduct_name")String aproduct_name,@Param("abrand") String abrand,@Param("aspecifications")String aspecifications,@Param("acategory")String acategory,@Param("aunit")String aunit,@Param("anumber") String anumber,@Param("abuy_price")String abuy_price,@Param("aprice") String aprice,@Param("adepartment") String adepartment,@Param("aname") String aname,@Param("ause") String ause,@Param("atime") String time);

    /**
     * 分页查询apply
     * @param pageStart
     * @param pageEnd
     * @return
     */
    @Select("select * from apply order by atime desc limit #{pageStart},#{pageEnd}")
    ArrayList<Apply> getApply(@Param("pageStart") int pageStart, @Param("pageEnd") int pageEnd);
    /**
     * school分页查询apply
     * @param pageStart
     * @param pageEnd
     * @return
     */
    @Select("select * from apply where adepartment=#{adepartment} order by atime desc limit #{pageStart},#{pageEnd}")
    ArrayList<Apply> getSchoolApply(@Param("pageStart") int pageStart, @Param("pageEnd") int pageEnd,@Param("adepartment")String adepartment);
    /**
     * 模糊搜索apply
     * @param
     * @param searchtext
     * @return
     */
    @Select("select * from apply where aproduct_name like concat('%',#{searchtext},'%')")
    ArrayList<Apply> searchApply(@Param("searchtext") String searchtext);
    /**
     * school模糊搜索apply
     * @param
     * @param searchtext
     * @return
     */
    @Select("select * from apply where adepartment=#{adepartment} and aproduct_name like concat('%',#{searchtext},'%')")
    ArrayList<Apply> searchSchoolApply(@Param("searchtext") String searchtext,@Param("adepartment")String adepartment);

    /**
     * Apply的数量
     */

    @Select("select count(aid) from apply")
    int getApplyNum();
    /**
     * School Apply数量
     */


    @Select("select count(aid) from apply where adepartment=#{adepartment}")
    int getSchoolApplyNum(String adepartment);

    /**
     *获得单个库存
     * @return
     */
    @Select("select * from apply where aid=#{aid}")
    Apply getApplydata(@Param("aid") int aid);
    /**
     * 更新库存数量
     */
    @Update("update storehouse set snumber=#{snumber} where sid=#{sid}")
    int updateNumber(@Param("snumber") String snumber,@Param("sid") int sid);
    /**
     *入账
     */
    @Insert("insert into entry(eremit_account,eremit_money,eremit_note,estoreman,eleadman,edepartment,etime) values(#{eremit_account},#{eremit_money},#{eremit_note},#{estoreman},#{eleadman},#{edepartment},#{etime})")
    int addEntry(@Param("eremit_account")String eremit_account,@Param("eremit_money") String eremint_money,@Param("eremit_note")String eremint_note,@Param("estoreman")String estoreman,@Param("eleadman")String eleadman,@Param("edepartment")String edepartment,@Param("etime")String etime);
    /**
     * 分页查询入账
     * @param pageStart
     * @param pageEnd
     * @return
     */
    @Select("select * from entry order by etime desc limit #{pageStart},#{pageEnd}")
    ArrayList<Entry> getEntry(@Param("pageStart") int pageStart, @Param("pageEnd") int pageEnd);
    /**
     * 学校分页查询入账
     * @param pageStart
     * @param pageEnd
     * @return
     */
    @Select("select * from entry where edepartment=#{edepartment} order by etime desc limit #{pageStart},#{pageEnd}")
    ArrayList<Entry> getSchoolEntry(@Param("pageStart") int pageStart, @Param("pageEnd") int pageEnd,@Param("edepartment")String edepartment);
    /**
     * 模糊搜索entry
     * @param
     * @param searchtext
     * @return
     */
    @Select("select * from entry where eremit_account like concat('%',#{searchtext},'%')")
    ArrayList<Entry> searchEntry(@Param("searchtext") String searchtext);
    /**
     * school模糊搜索entry
     * @param
     * @param searchtext
     * @return
     */
    @Select("select * from entry where edepartment=#{edepartment} and eremit_account like concat('%',#{searchtext},'%')")
    ArrayList<Entry> searchSchoolEntry(@Param("searchtext") String searchtext,@Param("edepartment") String edepartment);
    /**
     * EntrySchool的数量
     */

    @Select("select count(eid) from entry where edepartment=#{edepartment}")
    int getSchoolEntryNum(@Param("edepartment")String edepartment);
    /**
     * Entry的数量
     */

    @Select("select count(eid) from entry")
    int getEntryNum();
    /**
     *获得单个库存
     * @return
     */
    @Select("select * from entry where eid=#{eid}")
    Entry getEntrydata(@Param("eid") int eid);

    /**
     * 导出apply
     * @param adepartment
     * @param starttime
     * @param endtime
     * @return
     */
    @Select("SELECT * FROM apply WHERE adepartment=#{adepartment} and atime BETWEEN STR_TO_DATE(#{starttime},'%Y-%m-%d') and STR_TO_DATE(#{endtime},'%Y-%m-%d')")
    ArrayList<Apply> exportApply(@Param("adepartment")String adepartment,@Param("starttime")String starttime,@Param("endtime")String endtime);
    /**
     * 导出store
     * @param starttime
     * @param endtime
     * @return
     */
    @Select("SELECT * FROM storehouse WHERE school=#{school} and stime BETWEEN STR_TO_DATE(#{starttime},'%Y-%m-%d') and STR_TO_DATE(#{endtime},'%Y-%m-%d')")
    ArrayList<Storehouse> exportStore(@Param("school")String school,@Param("starttime")String starttime,@Param("endtime")String endtime);
    /**
     * 导出entry
     * @param starttime
     * @param endtime
     * @return
     */
    @Select("SELECT * FROM entry WHERE edepartment=#{edepartment} and etime BETWEEN STR_TO_DATE(#{starttime},'%Y-%m-%d') and STR_TO_DATE(#{endtime},'%Y-%m-%d')")
    ArrayList<Entry> exportEntry(@Param("edepartment")String edepartment,@Param("starttime")String starttime,@Param("endtime")String endtime);
}
