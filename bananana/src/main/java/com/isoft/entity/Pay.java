package com.isoft.entity;

public class Pay {
    private int id;
    private int name_id;//名字id
    private String name;//名字
    private int class_hour;//学时
    private String money;//已交学费
    private int head_id;//老师名字id
    private String head_name;//老师名字
    private String class_name;//班级
    private String school;//学校
    private int schoolmaster_id;//校长id
    private String schoolmaster;//校长
    private String time;//时间
    public Pay(){

    }
    @Override
    public String toString() {
        return "Pay{" +
                "id=" + id +
                ", name_id=" + name_id +
                ", name='" + name + '\'' +
                ", class_hour=" + class_hour +
                ", money='" + money + '\'' +
                ", head_id=" + head_id +
                ", head_name='" + head_name + '\'' +
                ", class_name='" + class_name + '\'' +
                ", school='" + school + '\'' +
                ", schoolmaster_id=" + schoolmaster_id +
                ", schoolmaster='" + schoolmaster + '\'' +
                ", time='" + time + '\'' +
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

    public int getClass_hour() {
        return class_hour;
    }

    public void setClass_hour(int class_hour) {
        this.class_hour = class_hour;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getHead_id() {
        return head_id;
    }

    public void setHead_id(int head_id) {
        this.head_id = head_id;
    }

    public String getHead_name() {
        return head_name;
    }

    public void setHead_name(String head_name) {
        this.head_name = head_name;
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

    public int getSchoolmaster_id() {
        return schoolmaster_id;
    }

    public void setSchoolmaster_id(int schoolmaster_id) {
        this.schoolmaster_id = schoolmaster_id;
    }

    public String getSchoolmaster() {
        return schoolmaster;
    }

    public void setSchoolmaster(String schoolmaster) {
        this.schoolmaster = schoolmaster;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public Pay(int id, int name_id, String name, int class_hour, String money, int head_id, String head_name, String class_name, String school, int schoolmaster_id, String schoolmaster, String time) {
        this.id = id;
        this.name_id = name_id;
        this.name = name;
        this.class_hour = class_hour;
        this.money = money;
        this.head_id = head_id;
        this.head_name = head_name;
        this.class_name = class_name;
        this.school = school;
        this.schoolmaster_id = schoolmaster_id;
        this.schoolmaster = schoolmaster;
        this.time = time;
    }


}
