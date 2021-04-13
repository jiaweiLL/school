package com.isoft.entity;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

public class Storehouse {
    private int sid;
    private String sproduct_Name;//品名
    private String sbrand;//品牌
    private String specifications;//规格
    private String scategory;//类别
    private String sbuy_price;//采购价格
    private String sguide_price;//指导价格
    private String sunit;//计量单位
    private String snumber;//库存数量
    private String scollected_month;//本月领用
    private String snext_month;//下月申购
    private String school;//所属单位
    private String belong;//属于
    private String time;//
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }


    @Override
    public String toString() {
        return "Storehouse{" +
                "sid=" + sid +
                ", sproduct_Name='" + sproduct_Name + '\'' +
                ", sbrand='" + sbrand + '\'' +
                ", specifications='" + specifications + '\'' +
                ", scategory='" + scategory + '\'' +
                ", sbuy_price='" + sbuy_price + '\'' +
                ", sguide_price='" + sguide_price + '\'' +
                ", sunit='" + sunit + '\'' +
                ", snumber='" + snumber + '\'' +
                ", scollected_month='" + scollected_month + '\'' +
                ", snext_month='" + snext_month + '\'' +
                ", school='" + school + '\'' +
                ", belong='" + belong + '\'' +
                ", time='" + time + '\'' +
                '}';
    }


    public Storehouse(int sid, String sproduct_Name, String sbrand, String specifications, String scategory, String sbuy_price, String sguide_price, String sunit, String snumber, String scollected_month, String snext_month, String school, String belong, String time) {
        this.sid = sid;
        this.sproduct_Name = sproduct_Name;
        this.sbrand = sbrand;
        this.specifications = specifications;
        this.scategory = scategory;
        this.sbuy_price = sbuy_price;
        this.sguide_price = sguide_price;
        this.sunit = sunit;
        this.snumber = snumber;
        this.scollected_month = scollected_month;
        this.snext_month = snext_month;
        this.school = school;
        this.belong = belong;
        this.time = time;
    }


    public Storehouse(){

    }


    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSproduct_Name() {
        return sproduct_Name;
    }

    public void setSproduct_Name(String sproduct_Name) {
        this.sproduct_Name = sproduct_Name;
    }

    public String getSbrand() {
        return sbrand;
    }

    public void setSbrand(String sbrand) {
        this.sbrand = sbrand;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getScategory() {
        return scategory;
    }

    public void setScategory(String scategory) {
        this.scategory = scategory;
    }

    public String getSbuy_price() {
        return sbuy_price;
    }

    public void setSbuy_price(String sbuy_price) {
        this.sbuy_price = sbuy_price;
    }

    public String getSguide_price() {
        return sguide_price;
    }

    public void setSguide_price(String sguide_price) {
        this.sguide_price = sguide_price;
    }

    public String getSunit() {
        return sunit;
    }

    public void setSunit(String sunit) {
        this.sunit = sunit;
    }

    public String getSnumber() {
        return snumber;
    }

    public void setSnumber(String snumber) {
        this.snumber = snumber;
    }

    public String getScollected_month() {
        return scollected_month;
    }

    public void setScollected_month(String scollected_month) {
        this.scollected_month = scollected_month;
    }

    public String getSnext_month() {
        return snext_month;
    }

    public void setSnext_month(String snext_month) {
        this.snext_month = snext_month;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



}
