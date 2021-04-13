package com.isoft.entity;
//申请
public class Apply {
    private int aid;//
    private String aproduct_name;//品名
    private String abrand;//品牌
    private String aspecifications;//规格
    private String acategory;//类别
    private String aunit;//计量单位
    private String anumber;//数量
    private String abuy_price;//单价
    private String aprice;//金额
    private String adepartment;//领用部门
    private String aname;//领用人
    private String ause;//用途
    private String atime;//时间
    @Override
    public String toString() {
        return "Apply{" +
                "aid=" + aid +
                ", aproduct_name='" + aproduct_name + '\'' +
                ", abrand='" + abrand + '\'' +
                ", aspecifications='" + aspecifications + '\'' +
                ", acategory='" + acategory + '\'' +
                ", aunit='" + aunit + '\'' +
                ", anumber='" + anumber + '\'' +
                ", abuy_price='" + abuy_price + '\'' +
                ", aprice='" + aprice + '\'' +
                ", adepartment='" + adepartment + '\'' +
                ", aname='" + aname + '\'' +
                ", ause='" + ause + '\'' +
                ", atime='" + atime + '\'' +
                '}';
    }


    public String getAnumber() {
        return anumber;
    }


    public void setAnumber(String anumber) {
        this.anumber = anumber;
    }

    public String getAprice() {
        return aprice;
    }

    public void setAprice(String aprice) {
        this.aprice = aprice;
    }


    public Apply(int aid, String aproduct_name, String abrand, String aspecifications, String acategory, String aunit, String anumber, String abuy_price, String aprice, String adepartment, String aname, String ause, String atime) {
        this.aid = aid;
        this.aproduct_name = aproduct_name;
        this.abrand = abrand;
        this.aspecifications = aspecifications;
        this.acategory = acategory;
        this.aunit = aunit;
        this.anumber = anumber;
        this.abuy_price = abuy_price;
        this.aprice = aprice;
        this.adepartment = adepartment;
        this.aname = aname;
        this.ause = ause;
        this.atime = atime;
    }


    public Apply(){

    }


    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAproduct_name() {
        return aproduct_name;
    }

    public void setAproduct_name(String aproduct_name) {
        this.aproduct_name = aproduct_name;
    }

    public String getAbrand() {
        return abrand;
    }

    public void setAbrand(String abrand) {
        this.abrand = abrand;
    }

    public String getAspecifications() {
        return aspecifications;
    }

    public void setAspecifications(String aspecifications) {
        this.aspecifications = aspecifications;
    }

    public String getAcategory() {
        return acategory;
    }

    public void setAcategory(String acategory) {
        this.acategory = acategory;
    }

    public String getAunit() {
        return aunit;
    }

    public void setAunit(String aunit) {
        this.aunit = aunit;
    }


    public String getAbuy_price() {
        return abuy_price;
    }

    public void setAbuy_price(String abuy_price) {
        this.abuy_price = abuy_price;
    }


    public String getAdepartment() {
        return adepartment;
    }

    public void setAdepartment(String adepartment) {
        this.adepartment = adepartment;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getAuse() {
        return ause;
    }

    public void setAuse(String ause) {
        this.ause = ause;
    }

    public String getAtime() {
        return atime;
    }

    public void setAtime(String atime) {
        this.atime = atime;
    }




}
