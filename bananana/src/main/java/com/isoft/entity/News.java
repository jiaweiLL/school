package com.isoft.entity;
//签到和动态
public class News {
    private int id;//自增id
    private int name_id;//名字id
    private String name;//名字
    private String title;//主题
    private String text;//内容
    private String sign_class;//签到班级
    private String starttime;//签到开始时间
    private String endtime;//签到结束时间
    private String time;//事件创建时间
    private String typenews;//事件类型
    public News(){

    }
    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", name_id=" + name_id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", sign_class='" + sign_class + '\'' +
                ", starttime='" + starttime + '\'' +
                ", endtime='" + endtime + '\'' +
                ", time='" + time + '\'' +
                ", typenews='" + typenews + '\'' +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName_id() {
        return name_id;
    }

    public void setName_id(int name_id) {
        this.name_id = name_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSign_class() {
        return sign_class;
    }

    public void setSign_class(String sign_class) {
        this.sign_class = sign_class;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTypenews() {
        return typenews;
    }

    public void setTypenews(String typenews) {
        this.typenews = typenews;
    }


    public News(int id, int name_id, String name, String title, String text, String sign_class, String starttime, String endtime, String time, String typenews) {
        this.id = id;
        this.name_id = name_id;
        this.name = name;
        this.title = title;
        this.text = text;
        this.sign_class = sign_class;
        this.starttime = starttime;
        this.endtime = endtime;
        this.time = time;
        this.typenews = typenews;
    }


}
