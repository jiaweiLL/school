package com.isoft.dao;

import com.isoft.entity.Sys;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

public interface PayDao {
    /**
     * 插入支付记录
     * @param name_id
     * @param name
     * @param class_hour
     * @param money
     * @param head_id
     * @param head_name
     * @param class_name
     * @param school
     * @param schoolmaster_id
     * @param schoolmaster
     * @param time
     * @return
     */
    @Insert("INSERT INTO pay(name_id,name,class_hour,money,head_id,head_name,class_name,school,schoolmaster_id,schoolmaster,time) VALUES(#{name_id},#{name},#{class_hour},#{money},#{head_id},#{head_name},#{class_name},#{school},#{schoolmaster_id},#{schoolmaster},#{time})")
    int payupdateStudent(@Param("name_id") int name_id, @Param("name") String name, @Param("class_hour") int class_hour, @Param("money") String money, @Param("head_id") int head_id, @Param("head_name") String head_name, @Param("class_name") String class_name, @Param("school") String school, @Param("schoolmaster_id") int schoolmaster_id, @Param("schoolmaster") String schoolmaster, @Param("time") String time);
    /**
     * 更新学生总缴费
     * @param class_hour
     * @param id
     * @param teacher_id
     * @return
     */
    @Update("UPDATE user SET class_hour=#{class_hour},total_pay=#{total_pay},renew=#{renew} WHERE id=#{id} and head_id=#{teacher_id}")
    int updata_totalpay(@Param("class_hour") int class_hour,@Param("total_pay") int total_pay,@Param("renew") int renew,@Param("id") int id,@Param("teacher_id") int teacher_id);
    /**
     * 校长模糊搜索学生
     * @param
     * @param searchtext
     * @return
     */
    @Select("select * from user where schoolmaster_id=#{schoolmaster_id} and name like concat('%',#{searchtext},'%')")
    ArrayList<Sys> schoolmasterSearch(@Param("schoolmaster_id")  int schoolmaster_id, @Param("searchtext") String searchtext);
}
