package com.isoft.dao;

import com.isoft.entity.Resource;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

public interface ResourceDao {
    @Select("select * from resource where coursetype='轮播图'")
    ArrayList<Resource> getRollPicture();
    /**
     * 修改轮播图
     * @param url
     * @param putid
     * @param putname
     * @param time
     * @param title
     * @return
     */
    @Update("update resource set url=#{url},putid=#{putid},putname=#{putname},time=#{time} where title=#{title} and coursetype='轮播图'")
    int updateRollPicture(@Param("url")String url,@Param("putid")int putid,@Param("putname")String putname,@Param("time")String time,@Param("title")String title);

    /**
     *  add公告视频
     */
    @Insert("insert into resource(url,title,text,content,filetype,coursetype,putid,putname,time) values(#{url},#{title},#{text},#{content},'video&photo','公告视频',#{putid},#{putname},#{time})")
    int addNoticeVideo(@Param("url")String url,@Param("title")String title,@Param("text")String text,@Param("content")String content,@Param("putid")int putid,@Param("putname") String putname,@Param("time")String time);

    /**
     * 查询公告视频的数量
     * @param
     * @return
     */
    @Select("select count(id) from resource where coursetype='公告视频'")
    int getNoticeVideoNum();


    /**
     * 分页查询动态数据
     * @param
     * @param pageStart
     * @param pageEnd
     * @return
     */
    @Select("select * from resource where coursetype='公告视频' order by time desc limit #{pageStart},#{pageEnd}")
    ArrayList<Resource> getNoticeVideoSome(@Param("pageStart") int pageStart,@Param("pageEnd") int pageEnd);
    /**
     *  add公告
     */
    @Insert("insert into resource(url,title,content,filetype,coursetype,putid,putname,time) values(#{url},#{title},#{content},'notice','公告',#{putid},#{putname},#{time})")
    int addNotice(@Param("url")String url,@Param("title")String title,@Param("content")String content,@Param("putid")int putid,@Param("putname") String putname,@Param("time")String time);

    /**
     * 查询公告视频的数量
     * @param
     * @return
     */
    @Select("select count(id) from resource where coursetype='公告'")
    int getNoticeNum();


    /**
     * 分页查询公告数据
     * @param
     * @param pageStart
     * @param pageEnd
     * @return
     */
    @Select("select * from resource where coursetype='公告' order by time desc limit #{pageStart},#{pageEnd}")
    ArrayList<Resource> getNoticeSome(@Param("pageStart") int pageStart,@Param("pageEnd") int pageEnd);

    /**
     * 获得一条公告数据
     * @param id
     * @return
     */
    @Select("select * from resource where coursetype='公告' and id=#{id}")
    Resource getNoticeData(@Param("id") int id);



    /**
     *  add公告视频
     */
    @Insert("insert into resource(url,title,text,content,filetype,coursetype,putid,putname,time) values(#{url},#{title},#{text},#{content},'video&photo',#{coursetype},#{putid},#{putname},#{time})")
    int addDance(@Param("url")String url,@Param("title")String title,@Param("text")String text,@Param("content")String content,@Param("coursetype")String coursetype,@Param("putid")int putid,@Param("putname") String putname,@Param("time")String time);


    /**
     * 查询Dance的数量
     * @param
     * @return
     */
    @Select("select count(id) from resource where coursetype=#{coursetype}")
    int getDanceNum(@Param("coursetype")String coursetype);


    /**
     * 分页查询公告数据
     * @param
     * @param pageStart
     * @param pageEnd
     * @return
     */
    @Select("select * from resource where coursetype=#{coursetype} order by time desc limit #{pageStart},#{pageEnd}")
    ArrayList<Resource> getDanceSome(@Param("pageStart") int pageStart,@Param("pageEnd") int pageEnd,@Param("coursetype")String coursetype);

    /**
     * 获得一条Dance数据
     * @param id
     * @return
     */
    @Select("select * from resource where id=#{id}")
    Resource getDanceData(@Param("id") int id);
}
