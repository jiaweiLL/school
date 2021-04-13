package com.isoft.entity;

public class Sign {
    private int id;//自增id
    private int newsid;//事件id
    private int name_id;//名字id
    private String name;//名字
    private String teacher_id;//老师id
    private String teacher;//老师
    private String class_name;//所属班级
    private String school;//所属学校
    private String time;//签到时间
    public Sign(){

    }
    @Override
    public String toString() {
        return "Sign{" +
                "id=" + id +
                ", newsid=" + newsid +
                ", name_id=" + name_id +
                ", name='" + name + '\'' +
                ", teacher_id='" + teacher_id + '\'' +
                ", teacher='" + teacher + '\'' +
                ", class_name='" + class_name + '\'' +
                ", school='" + school + '\'' +
                ", time='" + time + '\'' +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNewsid() {
        return newsid;
    }

    public void setNewsid(int newsid) {
        this.newsid = newsid;
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

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public Sign(int id, int newsid, int name_id, String name, String teacher_id, String teacher, String class_name, String school, String time) {
        this.id = id;
        this.newsid = newsid;
        this.name_id = name_id;
        this.name = name;
        this.teacher_id = teacher_id;
        this.teacher = teacher;
        this.class_name = class_name;
        this.school = school;
        this.time = time;
    }


}
