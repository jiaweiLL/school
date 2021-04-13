package com.isoft.servlet;

import com.isoft.entity.Consultation;
import com.isoft.service.MarketService;
import com.isoft.service.PersonnelSerivice;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@WebServlet("/MarketServlet")
public class MarketServlet extends HttpServlet {
    private MarketService marketService;
    private static final String UPLOAD_DIRECTORY = "upload";
    public MarketServlet() {
        this.marketService = new MarketService();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String args=request.getParameter("method");
        switch (args){
            case "consultAddStudent":
                consultAddStudent(request,response);
                break;
            case "getConsultSome":
                getConsultSome(request,response);
                break;
            case "searchConsult":
                searchConsult(request,response);
                break;
            case "getConsultStudent":
                getConsultStudent(request,response);
                break;
            case "updataData":
                updataData(request,response);
                break;
        }
    }

    protected void updataData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为j
        String cname=request.getParameter("cname");
        String cphone=request.getParameter("cphone");
        String csex=request.getParameter("csex");
        String cage=request.getParameter("cage");
        String cparents=request.getParameter("cparents");
        String cintroduce=request.getParameter("cintroduce");
        String consultation_method=request.getParameter("consultation_method");
        String cpurpose=request.getParameter("cpurpose");
        String consultation_course=request.getParameter("consultation_course");
        String cfollow_status=request.getParameter("cfollow_status");
        String creturn_visit=request.getParameter("creturn_visit");
        String communication_content=request.getParameter("communication_content");
        String consulting_school=request.getParameter("consulting_school");
        String channel=request.getParameter("channel");
        String csalesman=request.getParameter("csalesman");
        int cid=Integer.parseInt(request.getParameter("cid"));
        int rs=marketService.updataData(cname,cphone,csex,cage,cparents,cintroduce,consultation_method,cpurpose,consultation_course,cfollow_status,creturn_visit,communication_content,consulting_school,channel,csalesman,cid);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(rs);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void getConsultStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        String id1 = request.getParameter("cid") ;
        int cid=Integer.parseInt(id1);
        Consultation consultation=marketService.getConsultStudent(cid);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(consultation);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void searchConsult(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        String searchtext=request.getParameter("searchtext");
        ArrayList<Consultation> list=new ArrayList<>();
        list=marketService.searchConsult(searchtext);
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
    protected void getConsultSome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        ArrayList<Consultation> list=new ArrayList<>();
        int page=Integer.parseInt(request.getParameter("page"));
        int pageNum=Integer.parseInt(request.getParameter("pageNum"));
        int num=marketService.getConsultNum();
        int pages = (int) Math.ceil(num/(pageNum*1.0));
        int pageStart = (page - 1) * pageNum;
        int pageEnd=pageStart+pageNum;
        list=marketService.getConsultSome(pageStart,pageNum);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(list);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void consultAddStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为j
        String cname=request.getParameter("cname");
        String cphone=request.getParameter("cphone");
        String csex=request.getParameter("csex");
        String cage=request.getParameter("cage");
        String cparents=request.getParameter("cparents");
        String cintroduce=request.getParameter("cintroduce");
        String consultation_method=request.getParameter("consultation_method");
        String cpurpose=request.getParameter("cpurpose");
        String consultation_course=request.getParameter("consultation_course");
        String cfollow_status=request.getParameter("cfollow_status");
        String creturn_visit=request.getParameter("creturn_visit");
        String communication_content=request.getParameter("communication_content");
        String consulting_school=request.getParameter("consulting_school");
        String channel=request.getParameter("channel");
        String csalesman=request.getParameter("csalesman");
        long time1 = System.currentTimeMillis();
        String ctime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time1);
        int rs=marketService.consultAddStudent(cname,cphone,csex,cage,cparents,cintroduce,consultation_method,cpurpose,consultation_course,cfollow_status,creturn_visit,communication_content,consulting_school,channel,csalesman,ctime);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(rs);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
}
