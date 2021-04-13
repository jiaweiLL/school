package com.isoft.entity;

public class Sys {
    private int id;//自增id
    private String sno;//学号·账号
    private String password;//密码
    private String name;//名字
    private String sex;//性别
    private String age;//生日
    private String address;//地址
    private int head_id;//上级id
    private String head_name;//上级名字
    private String job;//职能（老师，人事）
    private String teacher_level;//老师级别
    private String parents;//父母
    private String phone;//电话
    private String curriculum;//所属课程（拉丁）
    private String level;//（职能级别）
    private String class_name;//班级名称
    private int class_hour;//课时
    private String school;//所属学校
    private int schoolmaster_id;//校长id
    private String schoolmaster;//校长名字
    private int total_pay;//总支付
    private int renew;//续费
    private String role;//角色
    private String power;//权限
    private String time;//入学时间，入职时间
    public Sys(){

    }

    @Override
    public String toString() {
        return "Sys{" +
                "id=" + id +
                ", sno='" + sno + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", head_id=" + head_id +
                ", head_name='" + head_name + '\'' +
                ", job='" + job + '\'' +
                ", teacher_level='" + teacher_level + '\'' +
                ", parents='" + parents + '\'' +
                ", phone='" + phone + '\'' +
                ", curriculum='" + curriculum + '\'' +
                ", level='" + level + '\'' +
                ", class_name='" + class_name + '\'' +
                ", class_hour=" + class_hour +
                ", school='" + school + '\'' +
                ", schoolmaster_id=" + schoolmaster_id +
                ", schoolmaster='" + schoolmaster + '\'' +
                ", total_pay=" + total_pay +
                ", renew=" + renew +
                ", role='" + role + '\'' +
                ", power='" + power + '\'' +
                ", time='" + time + '\'' +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getTeacher_level() {
        return teacher_level;
    }

    public void setTeacher_level(String teacher_level) {
        this.teacher_level = teacher_level;
    }

    public String getParents() {
        return parents;
    }

    public void setParents(String parents) {
        this.parents = parents;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public int getClass_hour() {
        return class_hour;
    }

    public void setClass_hour(int class_hour) {
        this.class_hour = class_hour;
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

    public int getTotal_pay() {
        return total_pay;
    }

    public void setTotal_pay(int total_pay) {
        this.total_pay = total_pay;
    }

    public int getRenew() {
        return renew;
    }

    public void setRenew(int renew) {
        this.renew = renew;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public Sys(int id, String sno, String password, String name, String sex, String  age, String address, int head_id, String head_name, String job, String teacher_level, String parents, String phone, String curriculum, String level, String class_name, int class_hour, String school, int schoolmaster_id, String schoolmaster, int total_pay, int renew, String role, String power, String time) {
        this.id = id;
        this.sno = sno;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.address = address;
        this.head_id = head_id;
        this.head_name = head_name;
        this.job = job;
        this.teacher_level = teacher_level;
        this.parents = parents;
        this.phone = phone;
        this.curriculum = curriculum;
        this.level = level;
        this.class_name = class_name;
        this.class_hour = class_hour;
        this.school = school;
        this.schoolmaster_id = schoolmaster_id;
        this.schoolmaster = schoolmaster;
        this.total_pay = total_pay;
        this.renew = renew;
        this.role = role;
        this.power = power;
        this.time = time;
    }


}
