package com.isoft.dao;

import com.isoft.entity.Consultation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

public interface MarketDao {
    /**
     * 添加意向学生
     * @param cname
     * @param cphone
     * @param csex
     * @param cage
     * @param cparents
     * @param cintroduce
     * @param consultation_method
     * @param cpurpose
     * @param consultation_course
     * @param cfollow_status
     * @param creturn_visit
     * @param communication_content
     * @param consulting_school
     * @param channel
     * @param csaleman
     * @param ctime
     * @return
     */
    @Insert("insert into consultation(cname,cphone,csex,cage,cparents,cintroduce,consultation_method,cpurpose,consultation_course,cfollow_status,creturn_visit,communication_content,consulting_school,channel,csalesman,ctime) values(#{cname},#{cphone},#{csex},#{cage},#{cparents},#{cintroduce},#{consultation_method},#{cpurpose},#{consultation_course},#{cfollow_status},#{creturn_visit},#{communication_content},#{consulting_school},#{channel},#{csaleman},#{ctime})")
    int consultAddStudent(@Param("cname") String cname,@Param("cphone") String cphone,@Param("csex") String csex,@Param("cage") String cage,@Param("cparents") String cparents,@Param("cintroduce") String cintroduce,@Param("consultation_method") String consultation_method,@Param("cpurpose") String cpurpose,@Param("consultation_course") String consultation_course,@Param("cfollow_status") String cfollow_status,@Param("creturn_visit") String creturn_visit,@Param("communication_content")String communication_content,@Param("consulting_school")String consulting_school,@Param("channel") String channel,@Param("csaleman")String csaleman,@Param("ctime")String ctime);

    /**
     * 跟新意向学生数据
     * @param cname
     * @param cphone
     * @param csex
     * @param cage
     * @param cparents
     * @param cintroduce
     * @param consultation_method
     * @param cpurpose
     * @param consultation_course
     * @param cfollow_status
     * @param creturn_visit
     * @param communication_content
     * @param consulting_school
     * @param channel
     * @param csaleman
     * @param cid
     * @return
     */
    @Update("update consultation set cname=#{cname},cphone=#{cphone},csex=#{csex},cage=#{cage},cparents=#{cparents},cintroduce=#{cintroduce},consultation_method=#{consultation_method},cpurpose=#{cpurpose},consultation_course=#{consultation_course},cfollow_status=#{cfollow_status},creturn_visit=#{creturn_visit},communication_content=#{communication_content},consulting_school=#{consulting_school},channel=#{channel},csalesman=#{csaleman} where cid=#{cid}")
    int updataData(@Param("cname") String cname,@Param("cphone") String cphone,@Param("csex") String csex,@Param("cage") String cage,@Param("cparents") String cparents,@Param("cintroduce") String cintroduce,@Param("consultation_method") String consultation_method,@Param("cpurpose") String cpurpose,@Param("consultation_course") String consultation_course,@Param("cfollow_status") String cfollow_status,@Param("creturn_visit") String creturn_visit,@Param("communication_content")String communication_content,@Param("consulting_school")String consulting_school,@Param("channel") String channel,@Param("csaleman")String csaleman,@Param("cid") int cid);
    /**
     *获得意向学生
     * @return
     */
    @Select("select * from consultation where cid=#{cid}")
    Consultation getConsultStudent(@Param("cid") int cid);
    /**
     * 意向学生的数量
     */

    @Select("select count(cid) from consultation")
    int getConsultNum();

    /**
     * 分页查询
     * @param pageStart
     * @param pageEnd
     * @return
     */
    @Select("select * from consultation order by ctime desc limit #{pageStart},#{pageEnd}")
    ArrayList<Consultation> getConsultSome(@Param("pageStart") int pageStart,@Param("pageEnd") int pageEnd);
    /**
     * 模糊搜索学生
     * @param
     * @param searchtext
     * @return
     */
    @Select("select * from consultation where cname like concat('%',#{searchtext},'%')")
    ArrayList<Consultation> searchConsult(@Param("searchtext") String searchtext);



}
