package com.isoft.entity;

public class order {
    private String consignee;

    @Override
    public String toString() {
        return "order{" +
                "consignee='" + consignee + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", postscript='" + postscript + '\'' +
                ", print_info='" + print_info + '\'' +
                ", order_price=" + order_price +
                '}';
    }

    private String address;
    private String mobile;
    private String postscript;
    private String print_info;
    private Double order_price;
    public order(String consignee, String address, String mobile, String postscript, String print_info, Double order_price) {
        this.consignee = consignee;
        this.address = address;
        this.mobile = mobile;
        this.postscript = postscript;
        this.print_info = print_info;
        this.order_price = order_price;
    }


    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPostscript() {
        return postscript;
    }

    public void setPostscript(String postscript) {
        this.postscript = postscript;
    }

    public String getPrint_info() {
        return print_info;
    }

    public void setPrint_info(String print_info) {
        this.print_info = print_info;
    }

    public Double getOrder_price() {
        return order_price;
    }

    public void setOrder_price(Double order_price) {
        this.order_price = order_price;
    }


    public order(){

    }

}
