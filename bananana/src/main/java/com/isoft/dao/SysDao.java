package com.isoft.dao;

import com.isoft.entity.Sys;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

public interface SysDao {
    /**
     * 添加学生
     */
    @Insert("INSERT INTO user(NAME,sno,PASSWORD,sex,age,address,parents,phone,curriculum,class_hour,head_id,head_name,total_pay,renew,role,class_name,school,schoolmaster,schoolmaster_id,level,time,job) VALUES(#{name},#{sno},#{password},#{sex},#{age},#{address},#{parents},#{phone},#{curriculum},#{class_hour},#{head_id},#{head},#{renew},#{renew},'1',#{class_name},#{school},#{schoolmaster},#{schoolmaster_id},#{level},#{time},'学生')")
    int addstudent(@Param("name") String name,@Param("sno") String sno,@Param("password") String password,@Param("sex") String sex,@Param("age") String  age,@Param("address") String address,@Param("parents") String parents,@Param("phone") String phone,@Param("curriculum") String curriculum,@Param("class_hour") int class_hour,@Param("head") String head,@Param("head_id") int head_id,@Param("renew") int renew,@Param("class_name") String class_name,@Param("school")String school,@Param("schoolmaster")String schoolmaster,@Param("schoolmaster_id") int schoolmaster_id,@Param("level")String level,@Param("time") String time);

    /*
    登陆验证
     */
    @Select("select * from user where sno=#{sno} and password=#{password}")
    Sys logincheck(@Param("sno") String phone, @Param("password") String password);
    /*
    账号验证
     */
    @Select("select * from user where sno=#{sno}")
    Sys checkSno(@Param("sno") String phone);
    /**
     * 获取该用户下的所有学生
     */
    @Select("select * from user where head_id=#{head_id}")
    ArrayList<Sys> getAllStudent(@Param("head_id") int head_id);

    /**
     *获得所有校长
     * @return
     */
    @Select("select * from user where power='2'")
    ArrayList<Sys> getAllschool();
    /**
     * 模糊搜索学生
     * @param
     * @param searchtext
     * @return
     */
    @Select("select * from user where head_id=#{head_id} and name like concat('%',#{searchtext},'%')")
    ArrayList<Sys> search(@Param("head_id")  int head_id,@Param("searchtext") String searchtext);


    /**
     *获取个人信息
     * @return
     */
    @Select("select * from user where id=#{id}")
    Sys getStudentDate(@Param("id") int id);
    /**
     * 更新数据
     */
    @Update("update user set name=#{name},password=#{password},sno=#{sno},sex=#{sex},age=#{age},address=#{address} ,level=#{level} where id=#{id}")
    int updateData(@Param("name") String name,@Param("password") String password,@Param("sno") String phone,@Param("sex") String sex,@Param("age") String age,@Param("address") String address,@Param("id") int id,@Param("level") String level);

    /**
     * 删除用户放入垃圾筒
     * @param id
     * @return
     */
    @Insert("insert into userdelet select * from user where id=#{id}")
    int userrubbish(@Param("id") int id);
    /**
     * 删除用户
     * @param id
     * @return
     */
    @Delete("delete from user where id=#{id}")
    int deleteDate(@Param("id") int id);

    /**
     * 获取学生对应老师的学生信息||校长对应老师的老师信息
     * @param id
     * @param teacher_id
     * @return
     */
    @Select("select * from user where id=#{id} and head_id=#{teacher_id}")
    Sys getStudent_teacher(@Param("id") int id,@Param("teacher_id") int teacher_id);
    /**
     * 更新学生课时
     * @param class_hour
     * @param id
     * @param teacher_id
     * @return
     */
    @Update("UPDATE user SET class_hour = #{class_hour} WHERE id = #{id} and head_id=#{teacher_id}")
    int updata_hour(@Param("class_hour") int class_hour,@Param("id") int id,@Param("teacher_id") int teacher_id);







    /**
     * school更新老师信息
     * @param name
     * @param password
     * @param phone
     * @param sex
     * @param age
     * @param address
     * @param
     * @param
     * @param id
     * @return
     */
    @Update("update user set name=#{name},password=#{password},sno=#{sno},sex=#{sex},age=#{age},address=#{address},phone=#{phone},school=#{school} where id=#{id}")
    int updataSchool(@Param("name") String name,@Param("password") String password,@Param("sno") String sno,@Param("sex") String sex,@Param("age") String age,@Param("address") String address,@Param("phone") String phone,@Param("school") String school,@Param("id") int id);
    /**
     * school更新老师信息
     * @param name
     * @param password
     * @param phone
     * @param sex
     * @param age
     * @param address
     * @param
     * @param curriculum
     * @param id
     * @return
     */
    @Update("update user set name=#{name},password=#{password},sno=#{sno},sex=#{sex},age=#{age},address=#{address},phone=#{phone},curriculum=#{curriculum} teacher_level=#{teacher_level} where id=#{id},")
    int updateTeacherData(@Param("name") String name,@Param("password") String password,@Param("sno") String sno,@Param("sex") String sex,@Param("age") String age,@Param("address") String address,@Param("phone") String phone,@Param("curriculum") String curriculum,@Param("id") int id,@Param("teacher_level") String level);

    /**
     * school更新学生信息
     * @param name
     * @param password
     * @param phone
     * @param sex
     * @param age
     * @param address
     * @param parents
     * @param curriculum
     * @param class_hour
     * @param id
     * @return
     */
    @Update("update user set name=#{name},password=#{password},sno=#{sno},sex=#{sex},age=#{age},address=#{address},parents=#{parents},phone=#{phone},curriculum=#{curriculum},class_hour=#{class_hour},class_name=#{class_name},level=#{level} where id=#{id}")
    int BupdteStudentData(@Param("name") String name,@Param("password") String password,@Param("sno") String sno,@Param("sex") String sex,@Param("age") String age,@Param("address") String address,@Param("parents") String parents,@Param("phone") String phone,@Param("curriculum") String curriculum,@Param("class_hour") int class_hour,@Param("id") int id,@Param("class_name") String class_name,@Param("level") String level);

    /**
     * 增加老师
     * @param name
     * @param phone
     * @param password
     * @param sex
     * @param age
     * @param address
     * @param phone
     * @param curriculum
     * @param
     * @param
     * @return
     */
    @Insert("INSERT INTO user(NAME,sno,PASSWORD,sex,age,address,phone,curriculum,head_id,head_name,role,school,time,teacher_level,schoolmaster_id,schoolmaster,power,job) VALUES(#{name},#{sno},#{password},#{sex},#{age},#{address},#{phone},#{curriculum},#{head_id},#{head},'2',#{school},#{time},#{teacher_level},#{head_id},#{head},'1','老师')")
    int addTeacher(@Param("name") String name,@Param("sno") String sno,@Param("password") String password,@Param("sex") String sex,@Param("age") String age,@Param("address") String address,@Param("phone") String phone,@Param("curriculum") String curriculum,@Param("head") String head,@Param("head_id") int head_id,@Param("school") String school,@Param("time") String time,@Param("teacher_level")String teacher_level);

    /**
     * 校长模糊搜索学生和老师
     * @param searchtext
     * @return
     */
    @Select("select * from wx_user where name like concat('%',#{searchtext},'%')")
    ArrayList<Sys> searchStudent(@Param("searchtext") String searchtext);

    /**
     * 转移学生
     * @param name
     * @param teacher_id
     * @param teacher
     * @param curriculum
     * @param class_hour
     * @param id
     * @return
     */
    @Update("update wx_user set name=#{name},teacher_id=#{teacher_id},teacher=#{teacher},curriculum=#{curriculum},class_hour=#{class_hour} where id=#{id}")
    int moveStudentData(@Param("name") String name,@Param("teacher_id") int teacher_id,@Param("teacher") String teacher,@Param("curriculum") String curriculum,@Param("class_hour") int class_hour,@Param("id") int id);

    @Select("select * from wx_user where name=#{teacher}")
    Sys selectMoveTeacher(@Param("teacher") String teacher);
}
