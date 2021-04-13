package com.isoft.servlet;

import com.isoft.entity.Sys;
import com.isoft.service.NewsService;
import com.isoft.service.PayService;
import com.isoft.service.SysService;
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

@WebServlet("/SchoolServlet")
public class SchoolServlet extends HttpServlet {
    private NewsService newsService;
    private SysService sysService;
    private PayService payService;
    private static final String UPLOAD_DIRECTORY = "upload";
    public SchoolServlet() {
        this.newsService = new NewsService();
        this.sysService = new SysService();
        this.payService=new PayService();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String args=request.getParameter("method");
        switch (args){
            case "updateTeacherData":
                updateTeacherData(request,response);
                break;
            case "schupdateStudentData":
                schupdateStudentData(request,response);
                break;
            case "addTeacher":
                addTeacher(request,response);
                break;
            case "putNotice":
                putNotice(request,response);
                break;
            case "schoolmasterSearch":
                schoolmasterSearch(request,response);
                break;
            case "payupdateStudent":
                payupdateStudent(request,response);
                break;
            case "updataSchool":
                updataSchool(request,response);
                break;
        }
    }
    protected void payupdateStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        String id1 = request.getParameter("id") ;
        int name_id=Integer.parseInt(id1);
        String name=request.getParameter("name");
        int head_id=Integer.parseInt(request.getParameter("head_id"));
        String head_name=request.getParameter("head_name");
        String school=request.getParameter("school");
        String schoolmaster=request.getParameter("schoolmaster");
        int schoolmaster_id=Integer.parseInt(request.getParameter("schoolmaster_id"));
        String class_name=request.getParameter("class_name");
        int class_hour=Integer.parseInt(request.getParameter("class_hour"));
        String money=request.getParameter("money");
        long time1 = System.currentTimeMillis();
        String time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time1);
        int rs=payService.payupdateStudent(name_id,name,class_hour,money,head_id,head_name,class_name,school,schoolmaster_id,schoolmaster,time);
        if(rs>0){
            Sys sys=sysService.getStudent_teacher(name_id,head_id);
            int now_Class_hour=sys.getClass_hour();
            int total_pay=sys.getTotal_pay();
            int renew=sys.getRenew();
            int now_total_pay=total_pay+Integer.parseInt(money);
            int now_renew=Integer.parseInt(money);
            now_Class_hour=now_Class_hour+class_hour;
            int up=payService.updata_totalpay(now_Class_hour,now_total_pay,now_renew,name_id,head_id);
        }
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(rs);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void schoolmasterSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        int schoolmaster_id =Integer.parseInt(request.getParameter("schoolmaster_id")) ;
        String searchtext=request.getParameter("searchtext");
        ArrayList<Sys> list=new ArrayList<>();
        list=payService.schoolmasterSearch(schoolmaster_id,searchtext);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(list);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void putNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        String name=request.getParameter("name");
        String title=request.getParameter("title");
        String text=request.getParameter("text");
        String name_id1=request.getParameter("name_id");
        int name_id=Integer.parseInt(name_id1);
        long time1 = System.currentTimeMillis();
        String time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time1);
        int rs=newsService.putNotice(name_id,name,title,text,time);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(rs);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void addTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        String head = request.getParameter("head") ;
        String name=request.getParameter("name");
        String address=request.getParameter("address");
        String password=request.getParameter("password");
        String sex=request.getParameter("sex");
        String age=request.getParameter("age");
        String sno=request.getParameter("sno");
        String curriculum=request.getParameter("curriculum");
        String teacher_id1=request.getParameter("head_id");
        int head_id=Integer.parseInt(teacher_id1);
        String phone=request.getParameter("phone");
        String time=request.getParameter("time");
        String teacher_level=request.getParameter("teacher_level");
        String school=request.getParameter("school");
        Sys check=sysService.CheckSno(sno);
        if(check!=null){
            int rs=-1;
            JSONArray jsonArray = new JSONArray() ;
            JSONArray json=JSONArray.fromObject(rs);
            String strJson=json.toString();
            Writer out = response.getWriter();
            out.write(strJson);
            out.flush();
        }else{
            int rs=sysService.addTeacher(name,sno,password,sex,age,address,phone,curriculum,head,head_id,school,time,teacher_level);
            JSONArray jsonArray = new JSONArray() ;
            JSONArray json=JSONArray.fromObject(rs);
            String strJson=json.toString();
            Writer out = response.getWriter();
            out.write(strJson);
            out.flush();
        }

    }
    protected void schupdateStudentData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        String id1 = request.getParameter("id") ;
        int id=Integer.parseInt(id1);
        String name=request.getParameter("name");
        String address=request.getParameter("address");
        String password=request.getParameter("password");
        String sex=request.getParameter("sex");
        String age=request.getParameter("age");
        String sno=request.getParameter("sno");
        String parents=request.getParameter("parents");
        String phone=request.getParameter("phone");
        int class_hour= Integer.parseInt(request.getParameter("class_hour"));
        String class_name=request.getParameter("class_name");
        String level=request.getParameter("level");
        String curriculum=request.getParameter("curriculum");
        int rs=sysService.schupdateStudentData(name,password,sno,sex,age,address,parents,phone,curriculum,class_hour,id,class_name,level);
        System.out.println(rs);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(rs);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void updateTeacherData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        String id1 = request.getParameter("id") ;
        int id=Integer.parseInt(id1);
        String name=request.getParameter("name");
        String address=request.getParameter("address");
        String password=request.getParameter("password");
        String sex=request.getParameter("sex");
        String age=request.getParameter("age");
        String phone=request.getParameter("phone");
        String sno=request.getParameter("sno");
        String curriculum=request.getParameter("curriculum");
        String teacher_level=request.getParameter("teacher_level");
        int rs=sysService.updataTeacherDate(name,password,sno,sex,age,address,phone,curriculum,id,teacher_level);
        System.out.println(rs);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(rs);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void updataSchool(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        String id1 = request.getParameter("id") ;
        int id=Integer.parseInt(id1);
        String name=request.getParameter("name");
        String address=request.getParameter("address");
        String password=request.getParameter("password");
        String sex=request.getParameter("sex");
        String age=request.getParameter("age");
        String phone=request.getParameter("phone");
        String sno=request.getParameter("sno");
        String school=request.getParameter("school");
        int rs=sysService.updataSchool(name,password,sno,sex,age,address,phone,school,id);
        System.out.println(rs);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(rs);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
}
