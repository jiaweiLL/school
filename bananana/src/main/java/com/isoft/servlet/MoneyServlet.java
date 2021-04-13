package com.isoft.servlet;

import com.isoft.entity.Apply;
import com.isoft.entity.Entry;
import com.isoft.entity.Storehouse;
import com.isoft.service.MoneyService;
import com.isoft.util.Excel;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

@WebServlet("/MoneyServlet")
public class MoneyServlet extends HttpServlet {
    private MoneyService moneyService;
    private Excel excel;
    private static final String UPLOAD_DIRECTORY = "upload";
    public MoneyServlet() {
        this.moneyService = new MoneyService();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String args=request.getParameter("method");
        switch (args){
            case "addStore":
                addStore(request,response);
                break;
            case "getStoreSome":
                getStoreSome(request,response);
                break;
            case "getStoredata":
                getStoredata(request,response);
                break;
            case "searchStore":
                searchStore(request,response);
                break;
            case "getApplydata":
                getApplydata(request,response);
                break;
            case "searchApply":
                searchApply(request,response);
                break;
            case "getApplySome":
                getApplySome(request,response);
                break;
            case "addApply":
                addApply(request,response);
                break;
            case "getEntrydata":
                getEntrydata(request,response);
                break;
            case "searchEntry":
                searchEntry(request,response);
                break;
            case "getEntrySome":
                getEntrySome(request,response);
                break;
            case "addEntry":
                addEntry(request,response);
                break;
            case "exportApply":
                try {
                    exportApply(request,response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
    protected void exportApply(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为j
        String coursetype=request.getParameter("coursetype");
        String adepartment=request.getParameter("adepartment");
        String starttime=request.getParameter("starttime");
        String endtime=request.getParameter("endtime");

        String receive=request.getParameter("receive");
        String subject =request.getParameter("subject");
        String msg="";
        String filename="";
        if(coursetype.equals("申请表")){
            ArrayList<Apply> list1=moneyService.exportApply(adepartment,starttime,endtime);
            msg ="申请信息表";
            filename=excel.applyExcel(list1);
        }else if(coursetype.equals("库存表")){
            ArrayList<Storehouse> list2=moneyService.exportStore(adepartment,starttime,endtime);
            msg ="库存信息表";
            filename=excel.storeExcel(list2);
        }else{
            ArrayList<Entry> list3=moneyService.exportEntry(adepartment,starttime,endtime);
            msg ="入账信息表";
            filename=excel.entryExcel(list3);
        }
        try {
            excel.sendMail(receive, subject, msg, filename);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        boolean rs=excel.deleteExcel(filename);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(rs);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void getStoredata(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        String id1 = request.getParameter("sid") ;
        int sid=Integer.parseInt(id1);
        Storehouse storehouse=moneyService.getStoredata(sid);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(storehouse);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void getApplydata(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        String id1 = request.getParameter("aid") ;
        int aid=Integer.parseInt(id1);
        Apply apply=moneyService.getApplydata(aid);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(apply);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void getEntrydata(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        String id1 = request.getParameter("eid") ;
        int eid=Integer.parseInt(id1);
        Entry entry=moneyService.getEntrydata(eid);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(entry);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void searchStore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        String searchtext=request.getParameter("searchtext");
        ArrayList<Storehouse> list=new ArrayList<>();
        String school=request.getParameter("school");
        if(!school.equals("none")){
            list=moneyService.searchSchoolStore(searchtext,school);
        }else{
            list=moneyService.searchStore(searchtext);
        }

        if(list!=null){
            JSONArray jsonArray = new JSONArray() ;
            JSONArray json=JSONArray.fromObject(list);
            String strJson=json.toString();
            Writer out = response.getWriter();
            out.write(strJson);
            out.flush();
        }else{
            int rs=1;
            JSONArray jsonArray = new JSONArray() ;
            JSONArray json=JSONArray.fromObject(rs);
            String strJson=json.toString();
            Writer out = response.getWriter();
            out.write(strJson);
            out.flush();
        }

    }
    protected void searchApply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        String searchtext=request.getParameter("searchtext");
        String school=request.getParameter("school");

        ArrayList<Apply> list=new ArrayList<>();
        if(!school.equals("none")){
            list=moneyService.searchSchoolApply(searchtext,school);
        }else{
            list=moneyService.searchApply(searchtext);
        }

        if(list!=null){
            JSONArray jsonArray = new JSONArray() ;
            JSONArray json=JSONArray.fromObject(list);
            String strJson=json.toString();
            Writer out = response.getWriter();
            out.write(strJson);
            out.flush();
        }else{
            int rs=1;
            JSONArray jsonArray = new JSONArray() ;
            JSONArray json=JSONArray.fromObject(rs);
            String strJson=json.toString();
            Writer out = response.getWriter();
            out.write(strJson);
            out.flush();
        }

    }
    protected void searchEntry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        String searchtext=request.getParameter("searchtext");
        String school=request.getParameter("school");
        ArrayList<Entry> list=new ArrayList<>();
        if(!school.equals("none")){
            list=moneyService.searchSchoolEntry(searchtext,school);
            if(list!=null){
                JSONArray jsonArray = new JSONArray() ;
                JSONArray json=JSONArray.fromObject(list);
                String strJson=json.toString();
                Writer out = response.getWriter();
                out.write(strJson);
                out.flush();
            }else{
                int rs=1;
                JSONArray jsonArray = new JSONArray() ;
                JSONArray json=JSONArray.fromObject(rs);
                String strJson=json.toString();
                Writer out = response.getWriter();
                out.write(strJson);
                out.flush();
            }
        }else{
            list=moneyService.searchEntry(searchtext);
            if(list!=null){
                JSONArray jsonArray = new JSONArray() ;
                JSONArray json=JSONArray.fromObject(list);
                String strJson=json.toString();
                Writer out = response.getWriter();
                out.write(strJson);
                out.flush();
            }else{
                int rs=1;
                JSONArray jsonArray = new JSONArray() ;
                JSONArray json=JSONArray.fromObject(rs);
                String strJson=json.toString();
                Writer out = response.getWriter();
                out.write(strJson);
                out.flush();
            }
        }


    }
    protected void getStoreSome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        ArrayList<Storehouse> list=new ArrayList<>();
        String school=request.getParameter("school");
        int page=Integer.parseInt(request.getParameter("page"));
        int pageNum=Integer.parseInt(request.getParameter("pageNum"));
        if(!school.equals("none")){
            int num=moneyService.getSchoolStoreNum(school);
            int pages = (int) Math.ceil(num/(pageNum*1.0));
            int pageStart = (page - 1) * pageNum;
            int pageEnd=pageStart+pageNum;
            list=moneyService.getSchoolStoreSome(pageStart,pageEnd,school);
            JSONArray jsonArray = new JSONArray() ;
            JSONArray json=JSONArray.fromObject(list);
            String strJson=json.toString();
            Writer out = response.getWriter();
            out.write(strJson);
            out.flush();
        }else{
            int num=moneyService.getStoreNum();
            int pages = (int) Math.ceil(num/(pageNum*1.0));
            int pageStart = (page - 1) * pageNum;
            int pageEnd=pageStart+pageNum;
            list=moneyService.getStoreSome(pageStart,pageEnd);
            JSONArray jsonArray = new JSONArray() ;
            JSONArray json=JSONArray.fromObject(list);
            String strJson=json.toString();
            Writer out = response.getWriter();
            out.write(strJson);
            out.flush();
        }

    }

    protected void getApplySome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        ArrayList<Apply> list=new ArrayList<>();
        int page=Integer.parseInt(request.getParameter("page"));
        int pageNum=Integer.parseInt(request.getParameter("pageNum"));
        String school=request.getParameter("school");
        System.out.println(school);
        if(!school.equals("none")){
            int num=moneyService.getSchoolApplyNum(school);
            int pages = (int) Math.ceil(num/(pageNum*1.0));
            int pageStart = (page - 1) * pageNum;
            int pageEnd=pageStart+pageNum;
            list=moneyService.getSchoolApplySome(pageStart,pageEnd,school);
            System.out.println("none");
            JSONArray jsonArray = new JSONArray() ;
            JSONArray json=JSONArray.fromObject(list);
            String strJson=json.toString();
            Writer out = response.getWriter();
            out.write(strJson);
            out.flush();
        }else{

            System.out.println("asd");
            int num=moneyService.getApplyNum();
            int pages = (int) Math.ceil(num/(pageNum*1.0));
            int pageStart = (page - 1) * pageNum;
            int pageEnd=pageStart+pageNum;
            list=moneyService.getApplySome(pageStart,pageEnd);
            JSONArray jsonArray = new JSONArray() ;
            JSONArray json=JSONArray.fromObject(list);
            String strJson=json.toString();
            Writer out = response.getWriter();
            out.write(strJson);
            out.flush();
        }

    }
    protected void getEntrySome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        ArrayList<Entry> list=new ArrayList<>();
        int page=Integer.parseInt(request.getParameter("page"));
        int pageNum=Integer.parseInt(request.getParameter("pageNum"));
        String school=request.getParameter("school");
        if(!school.equals("none")){
            int num=moneyService.getSchoolEntryNum(school);
            int pages = (int) Math.ceil(num/(pageNum*1.0));
            int pageStart = (page - 1) * pageNum;
            int pageEnd=pageStart+pageNum;
            list=moneyService.getSchoolEntrySome(pageStart,pageEnd,school);
            JSONArray jsonArray = new JSONArray() ;
            JSONArray json=JSONArray.fromObject(list);
            String strJson=json.toString();
            Writer out = response.getWriter();
            out.write(strJson);
            out.flush();
        }else{
            int num=moneyService.getEntryNum();
            int pages = (int) Math.ceil(num/(pageNum*1.0));
            int pageStart = (page - 1) * pageNum;
            int pageEnd=pageStart+pageNum;
            list=moneyService.getEntrySome(pageStart,pageEnd);
            JSONArray jsonArray = new JSONArray() ;
            JSONArray json=JSONArray.fromObject(list);
            String strJson=json.toString();
            Writer out = response.getWriter();
            out.write(strJson);
            out.flush();
        }

    }
    protected void addStore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为j
        String cproduct_Name=request.getParameter("cproduct_Name");
        String sbrand=request.getParameter("sbrand");
        String specifications=request.getParameter("specifications");
        String scategory=request.getParameter("scategory");
        String sbuy_price=request.getParameter("sbuy_price");
        String sguide_price=request.getParameter("sguide_price");
        String sunit=request.getParameter("sunit");
        String snumber=request.getParameter("snumber");
        String stime=request.getParameter("stime");
        String school=request.getParameter("school");
        int rs=moneyService.addStore(cproduct_Name,sbrand,specifications,scategory,sbuy_price,sguide_price,sunit,snumber,school,stime);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(rs);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void addApply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为j
        String aproduct_name=request.getParameter("aproduct_name");
        String abrand=request.getParameter("abrand");
        String aspecifications=request.getParameter("aspecifications");
        String acategory=request.getParameter("acategory");
        String abuy_price=request.getParameter("abuy_price");
        String aunit=request.getParameter("aunit");
        String anumber=request.getParameter("anumber");
        String atime=request.getParameter("atime");
        float price=Integer.parseInt(anumber)*Float.parseFloat(abuy_price);
        String aprice=""+price;
        String adepartment=request.getParameter("adepartment");
        String aname=request.getParameter("aname");
        String ause=request.getParameter("ause");
        int number=Integer.parseInt(request.getParameter("snumber"))-Integer.parseInt(anumber);
        String snumber=""+number;
        int sid=Integer.parseInt(request.getParameter("sid"));
        int rs1=moneyService.updateNumber(snumber,sid);
        int rs=moneyService.addApply(aproduct_name,abrand,aspecifications,acategory,aunit,anumber,abuy_price,aprice,adepartment,aname,ause,atime);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(rs);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void addEntry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为j
        String eremit_account=request.getParameter("eremit_account");
        String eremit_money=request.getParameter("eremit_money");
        String eremit_note=request.getParameter("eremit_note");
        String estoreman=request.getParameter("estoreman");
        String eleadman=request.getParameter("eleadman");
        String etime=request.getParameter("etime");
        String edepartment=request.getParameter("edepartment");
        int rs=moneyService.addEntry(eremit_account,eremit_money,eremit_note,estoreman,eleadman,edepartment,etime);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(rs);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
}
