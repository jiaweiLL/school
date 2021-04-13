package com.isoft.entity;
//意向人
public class Consultation {
    private int cid;//
    private String cname;//名字
    private String cphone;//手机号
    private String csex;//性别
    private String cage;//
    private String cparents;//父母
    private String cintroduce;//介绍人
    private String consultation_method;//咨询方法
    private String cpurpose;//意向
    private String consultation_course;//课程
    private String cfollow_status;//跟进状态
    private String creturn_visit;//回仿日期
    private String communication_content;//沟通内容
    private String consulting_school;//资讯学校
    private String channel;//销售人渠道
    private String csalesman;//销售人
    private String ctime;//时间
    public Consultation(){

    }
    @Override
    public String toString() {
        return "Consultation{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", cphone='" + cphone + '\'' +
                ", csex='" + csex + '\'' +
                ", cage='" + cage + '\'' +
                ", cparents='" + cparents + '\'' +
                ", cintroduce='" + cintroduce + '\'' +
                ", consultation_method='" + consultation_method + '\'' +
                ", cpurpose='" + cpurpose + '\'' +
                ", consultation_course='" + consultation_course + '\'' +
                ", cfollow_status='" + cfollow_status + '\'' +
                ", creturn_visit='" + creturn_visit + '\'' +
                ", communication_content='" + communication_content + '\'' +
                ", consulting_school='" + consulting_school + '\'' +
                ", channel='" + channel + '\'' +
                ", csalesman='" + csalesman + '\'' +
                ", ctime='" + ctime + '\'' +
                '}';
    }


    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    public String getCsex() {
        return csex;
    }

    public void setCsex(String csex) {
        this.csex = csex;
    }

    public String getCage() {
        return cage;
    }

    public void setCage(String cage) {
        this.cage = cage;
    }

    public String getCparents() {
        return cparents;
    }

    public void setCparents(String cparents) {
        this.cparents = cparents;
    }

    public String getCintroduce() {
        return cintroduce;
    }

    public void setCintroduce(String cintroduce) {
        this.cintroduce = cintroduce;
    }

    public String getConsultation_method() {
        return consultation_method;
    }

    public void setConsultation_method(String consultation_method) {
        this.consultation_method = consultation_method;
    }

    public String getCpurpose() {
        return cpurpose;
    }

    public void setCpurpose(String cpurpose) {
        this.cpurpose = cpurpose;
    }

    public String getConsultation_course() {
        return consultation_course;
    }

    public void setConsultation_course(String consultation_course) {
        this.consultation_course = consultation_course;
    }

    public String getCfollow_status() {
        return cfollow_status;
    }

    public void setCfollow_status(String cfollow_status) {
        this.cfollow_status = cfollow_status;
    }

    public String getCreturn_visit() {
        return creturn_visit;
    }

    public void setCreturn_visit(String creturn_visit) {
        this.creturn_visit = creturn_visit;
    }

    public String getCommunication_content() {
        return communication_content;
    }

    public void setCommunication_content(String communication_content) {
        this.communication_content = communication_content;
    }

    public String getConsulting_school() {
        return consulting_school;
    }

    public void setConsulting_school(String consulting_school) {
        this.consulting_school = consulting_school;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCsalesman() {
        return csalesman;
    }

    public void setCsalesman(String csalesman) {
        this.csalesman = csalesman;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }


    public Consultation(int cid, String cname, String cphone, String csex, String cage, String cparents, String cintroduce, String consultation_method, String cpurpose, String consultation_course, String cfollow_status, String creturn_visit, String communication_content, String consulting_school, String channel, String csalesman, String ctime) {
        this.cid = cid;
        this.cname = cname;
        this.cphone = cphone;
        this.csex = csex;
        this.cage = cage;
        this.cparents = cparents;
        this.cintroduce = cintroduce;
        this.consultation_method = consultation_method;
        this.cpurpose = cpurpose;
        this.consultation_course = consultation_course;
        this.cfollow_status = cfollow_status;
        this.creturn_visit = creturn_visit;
        this.communication_content = communication_content;
        this.consulting_school = consulting_school;
        this.channel = channel;
        this.csalesman = csalesman;
        this.ctime = ctime;
    }



}
