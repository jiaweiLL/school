package com.isoft.dao;

import com.isoft.entity.News;
import com.isoft.entity.Sign;
import com.isoft.entity.Sys;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.Date;

public interface NewsDao {
    /**
     * 查询老师所有动态
     * @param name
     * @return
     */
    @Select("select * from wx_news where user=#{name} and typenews='dynamic'")
    ArrayList<News> getAlldynamic(@Param("name") String name);

    /**
     * 查询动态的数量
     * @param
     * @return
     */
    @Select("select count(id) from news where name_id=#{name_id} and typenews='dynamic'")
    int getNewsNum(@Param("name_id") int name_id);


    /**
     * 分页查询动态数据
     * @param
     * @param pageStart
     * @param pageEnd
     * @return
     */
    @Select("select * from news where name_id=#{name_id} and typenews='dynamic' order by time desc limit #{pageStart},#{pageEnd}")
    ArrayList<News> getsome(@Param("name_id") int name_id,@Param("pageStart") int pageStart,@Param("pageEnd") int pageEnd);

    /**
     * 发布动态
     * @param name
     * @param title
     * @param text
     * @return
     */
    @Insert("insert into news(name_id,name,title,text,time,typenews) VALUES(#{name_id},#{name},#{title},#{text},#{time},'dynamic')")
    int putNotice(@Param("name_id") int user_id,@Param("name")String name, @Param("title") String title, @Param("text") String text,@Param("time") String time);

    /**
     * 发布签到动态
     * @param
     * @param text
     * @param
     * @return
     */
    @Insert("insert into news(name_id,name,title,text,sign_class,time,typenews,starttime,endtime) VALUES(#{name_id},#{name},'sign',#{text},#{sign_class},#{time},'sign',#{starttime},#{endtime})")
    int putSign(@Param("name")String name,  @Param("text") String text,@Param("sign_class") String sign_class,@Param("time")String time,@Param("name_id") int name_id,@Param("starttime") String starttime,@Param("endtime") String endtime);

    /**
     * 老师查询签到动态总数
     * @param
     * @return
     */
    @Select("select count(id) from news where name_id=#{name_id} and typenews='sign'")
    int getSignNum(@Param("name_id") int name_id);

    /**
     * 老师分页查询签到动态
     * @param name_id
     * @param pageStart
     * @param pageEnd
     * @return
     */
    @Select("select * from news where name_id=#{name_id} and typenews='sign' order by time desc limit #{pageStart},#{pageEnd}")
    ArrayList<News> getSign(@Param("name_id") int name_id,@Param("pageStart") int pageStart,@Param("pageEnd") int pageEnd);

    /**
     * 删除签到动态放入垃圾筒
     * @param id
     * @return
     */
    @Insert("insert into newsdelet select * from news where id=#{id}")
    int newsrubbish(@Param("id") int id);
    /**
     * 老师验证签到
     * @param newsid
     * @return
     */
    @Select("select * from sign where newsid=#{newsid}")
    ArrayList<Sign> getcheckSign(@Param("newsid") int newsid);
    /**
     * 获得其发布一条签到的签到数据
     * @param newsid
     * @return
     */
    @Select("select * from news where id=#{newsid}")
    News getnews(@Param("newsid") int newsid);
    /**
     * 获得所有未签到学生
     * @param
     * @return
     */
    @Select("select * from user u where u.class_name=#{sign_class} and u.head_id=#{teacher_id} and not EXISTS(select name from sign s where u.name = s.name and s.newsid=#{newsid})")
    ArrayList<Sys> getNoSign(@Param("teacher_id") int teacher_id, @Param("sign_class") String sign_class, @Param("newsid") int newsid);
    /**
     * 验证签到
     * @param newsid
     * @param
     * @return
     */
    @Select("select * from sign where newsid=#{newsid} and name_id=#{name_id}")
    Sign checkSign(@Param("newsid") int newsid, @Param("name_id") int name_id);
    /**
     * 老师替签到
     * @param newsid
     * @param name
     * @param time
     * @param teacher
     * @return
     */
    @Insert("insert into sign(newsid,name_id,name,time,teacher_id,teacher,school,class_name) VALUES(#{newsid},#{name_id},#{name},#{time},#{teacher_id},#{teacher},#{school},#{class_name})")
    int forSign(@Param("newsid") int newsid,@Param("name_id") int name_id,  @Param("name") String name,@Param("time") String time,@Param("teacher_id") int teacher_id,@Param("teacher") String teacher,@Param("school") String school,@Param("class_name") String class_name);





    /**
     * 学生查询签到动态总数
     * @param name_id
     * @param sign_class
     * @return
     */
    @Select("select count(id) from news where name_id=#{name_id} and sign_class=#{sign_class} and typenews='sign'")
    int getStuSignNum(@Param("name_id") int name_id,@Param("sign_class") String sign_class);


    /**
     * 学生分页查询签到动态
     * @param name_id
     * @param sign_class
     * @param pageStart
     * @param pageEnd
     * @return
     */
    @Select("select * from news where name_id=#{name_id} and sign_class=#{sign_class} and typenews='sign' order by time desc limit #{pageStart},#{pageEnd}")
    ArrayList<News> getStuSign(@Param("name_id") int name_id,@Param("sign_class") String sign_class,@Param("pageStart") int pageStart,@Param("pageEnd") int pageEnd);
    /**
     * 删除动态
     * @param id
     * @return
     */
    @Delete("delete from news where id=#{id}")
    int deleteSign(@Param("id") int id);

    /**
     * 学生签到
     * @param newsid
     * @param name
     * @param
     * @param
     * @return
     */
    @Insert("insert into sign(newsid,name_id,name,time,teacher_id,teacher,school,class_name) VALUES(#{newsid},#{name_id},#{name},#{time},#{head_id},#{head_name},#{school},#{class_name})")
    int toSign(@Param("newsid") int newsid,@Param("name_id") int name_id,  @Param("name") String name,@Param("time") String time,@Param("head_id") int head_id,@Param("head_name") String head_name,@Param("school")String school,@Param("class_name")String class_name);











    /**
     * 删除签到动态，包括此动态中已签到的学生
     * @param id
     * @return
     */
    @Delete("delete from wx_sign where signid=#{id}")
    int deleteSigned(@Param("id") int id);

    /**
     * 学生查询签到记录
     * @param name_id
     * @param pageStart
     * @param pageEnd
     * @return
     */
    @Select("select * from sign where name_id=#{name_id} order by time desc limit #{pageStart},#{pageEnd}")
    ArrayList<Sign> getPersonAllSign(@Param("name_id") int name_id, @Param("pageStart") int pageStart, @Param("pageEnd") int pageEnd);

    /**
     * 查询签到记录的数量
     * @param name_id
     * @return
     */
    @Select("select count(id) from sign where name_id=#{name_id}")
    int getPersonSignNum(@Param("name_id") int name_id);

    /**
     * 获得所有未签到老师
     * @param teacher_id
     * @param signid
     * @return
     */
    @Select("SELECT * from wx_user WHERE teacher_id=#{teacher_id} and name not in(SELECT name FROM wx_sign WHERE signid=#{signid})")
    ArrayList<Sys> getTeacherNoSign(@Param("teacher_id") int teacher_id,@Param("signid") int signid);

    /**
     * 老师查询校长发布动态或签到总数
     * @param user_id
     * @return
     */
    @Select("select count(id) from wx_news where user_id=#{user_id} and typenews='dynamic'")
    int getTeadynamicNum(@Param("user_id") int user_id);
    @Select("select count(id) from wx_news where user_id=#{user_id} and typenews='sign'")
    int getTeaSignNum(@Param("user_id") int user_id);

    /**
     * 老师查询校长发布动态或签到总数
     * @param user_id
     * @param pageStart
     * @param pageEnd
     * @return
     */
    @Select("select * from wx_news where user_id=#{user_id} and typenews='dynamic' order by time desc limit #{pageStart},#{pageEnd}")
    ArrayList<News> getTeadynamic(@Param("user_id") int user_id,@Param("pageStart") int pageStart,@Param("pageEnd") int pageEnd);
    @Select("select * from wx_news where user_id=#{user_id} and typenews='sign' order by time desc limit #{pageStart},#{pageEnd}")
    ArrayList<News> getTeaSign(@Param("user_id") int user_id,@Param("pageStart") int pageStart,@Param("pageEnd") int pageEnd);
}
