package com.isoft.entity;
//入账
public class Entry {
    private int eid;
    private String eremit_account;//汇款帐户
    private String eremit_money;//入账金额
    private String eremit_note;//入账事项
    private String estoreman;//保管员
    private String eleadman;//负责人
    private String edepartment;//所属部门
    private String etime;


    @Override
    public String toString() {
        return "Entry{" +
                "eid=" + eid +
                ", eremit_account='" + eremit_account + '\'' +
                ", eremit_money='" + eremit_money + '\'' +
                ", eremit_note='" + eremit_note + '\'' +
                ", estoreman='" + estoreman + '\'' +
                ", eleadman='" + eleadman + '\'' +
                ", edepartment='" + edepartment + '\'' +
                ", etime='" + etime + '\'' +
                '}';
    }


    public String getEdepartment() {
        return edepartment;
    }

    public void setEdepartment(String edepartment) {
        this.edepartment = edepartment;
    }


    public Entry(int eid, String eremit_account, String eremit_money, String eremit_note, String estoreman, String eleadman, String edepartment, String etime) {
        this.eid = eid;
        this.eremit_account = eremit_account;
        this.eremit_money = eremit_money;
        this.eremit_note = eremit_note;
        this.estoreman = estoreman;
        this.eleadman = eleadman;
        this.edepartment = edepartment;
        this.etime = etime;
    }


    public String getEremit_money() {
        return eremit_money;
    }

    public void setEremit_money(String eremit_money) {
        this.eremit_money = eremit_money;
    }

    public String getEremit_note() {
        return eremit_note;
    }

    public void setEremit_note(String eremit_note) {
        this.eremit_note = eremit_note;
    }




    public Entry(){

    }



    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getEremit_account() {
        return eremit_account;
    }

    public void setEremit_account(String eremit_account) {
        this.eremit_account = eremit_account;
    }


    public String getEstoreman() {
        return estoreman;
    }

    public void setEstoreman(String estoreman) {
        this.estoreman = estoreman;
    }

    public String getEleadman() {
        return eleadman;
    }

    public void setEleadman(String eleadman) {
        this.eleadman = eleadman;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }





}
