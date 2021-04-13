package com.isoft.servlet;

import com.isoft.entity.News;
import com.isoft.entity.Sign;
import com.isoft.entity.Sys;
import com.isoft.service.NewsService;
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

@WebServlet("/NewsServlet")
public class NewsServlet extends HttpServlet {
    private NewsService newsService;
    private SysService sysService;
    private static final String UPLOAD_DIRECTORY = "upload";
    public NewsServlet() {
        this.newsService = new NewsService();
        this.sysService = new SysService();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String args=request.getParameter("method");
        switch (args){
            case "putNotice":
                putNotice(request,response);
                break;
            case "getAlldynamic":
                getAlldynamic(request,response);
                break;
            case "putSign":
                putSign(request,response);
                break;
            case "getAllSign":
                getAllSign(request,response);
                break;
            case "deleteSign":
                deleteSign(request,response);
                break;
            case "getcheckSign":
                getcheckSign(request,response);
                break;
            case "getNoSign":
                getNoSign(request,response);
                break;
            case "forSign":
                forSign(request,response);
                break;
            case "getPersonAllSign":
                getPersonAllSign(request,response);
                break;
        }
    }
    protected void forSign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        String newsid1=request.getParameter("newsid");
        String teacher=request.getParameter("teacher");
        int teacher_id=Integer.parseInt(request.getParameter("teacher_id"));
        int name_id=Integer.parseInt(request.getParameter("name_id"));
        int newsid=Integer.parseInt(newsid1);
        Sys sys=sysService.getStudentDate(name_id);
        String name=sys.getName();
        String school=request.getParameter("school");
        String class_name=sys.getClass_name();
        long time1 = System.currentTimeMillis();
        String time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time1);
        int rs1=newsService.forSign(newsid,name_id,name,time,teacher_id,teacher,school,class_name);
        int now_Class_hour=sysService.getStudent_teacher(name_id,teacher_id).getClass_hour();
        now_Class_hour=now_Class_hour-2;
        int up=sysService.updata_hour(now_Class_hour,name_id,teacher_id);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(rs1);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void getNoSign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        String idnews = request.getParameter("newsid") ;
        int teacher_id=Integer.parseInt(request.getParameter("teacher_id"));
        int newsid=Integer.parseInt(idnews);
        String sign_class=newsService.getnews(newsid).getSign_class();
        ArrayList<Sys> list=new ArrayList<>();
        list=newsService.getNoSign(teacher_id,sign_class,newsid);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(list);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void getcheckSign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        String newsid1 = request.getParameter("newsid") ;
        int newsid=Integer.parseInt(newsid1);
        ArrayList<Sign> list=new ArrayList<>();
        list=newsService.getcheckSign(newsid);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(list);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void deleteSign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        String id1 = request.getParameter("id") ;
        int id=Integer.parseInt(id1);
        int rs1=newsService.newsrubbish(id);
        int rs=newsService.deleteSign(id);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(rs);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void getAllSign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        int name_id = Integer.parseInt(request.getParameter("name_id"));
        ArrayList<News> list=new ArrayList<>();
        int page=Integer.parseInt(request.getParameter("page"));
        int pageNum=Integer.parseInt(request.getParameter("pageNum"));
        int num=newsService.getSignNum(name_id);
        int pages = (int) Math.ceil(num/(pageNum*1.0));
        int pageStart = (page - 1) * pageNum;
        int pageEnd=pageStart+pageNum;
        list=newsService.getSign(name_id,pageStart,pageNum);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(list);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void putSign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        String name=request.getParameter("name");
        String text=request.getParameter("text1");
        String name_id1=request.getParameter("name_id");
        int name_id=Integer.parseInt(name_id1);
        String sign_class=request.getParameter("sign_class");
        String starttime=request.getParameter("starttime").trim();
        String endtime=request.getParameter("endtime").trim();
        long time1 = System.currentTimeMillis();
        String time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time1);
        int rs=newsService.putSign(name,text,sign_class,time,name_id,starttime,endtime);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(rs);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void getAlldynamic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        int name_id = Integer.parseInt(request.getParameter("name_id"));
        ArrayList<News> list=new ArrayList<>();
        int page=Integer.parseInt(request.getParameter("page"));
        int pageNum=Integer.parseInt(request.getParameter("pageNum"));
        int num=newsService.getNewsNum(name_id);
        int pages = (int) Math.ceil(num/(pageNum*1.0));
        int pageStart = (page - 1) * pageNum;
        int pageEnd=pageStart+pageNum;
        list=newsService.getsome(name_id,pageStart,pageNum);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(list);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void getPersonAllSign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        int name_id = Integer.parseInt(request.getParameter("name_id"));
        ArrayList<Sign> list=new ArrayList<>();
        int page=Integer.parseInt(request.getParameter("page"));
        int pageNum=Integer.parseInt(request.getParameter("pageNum"));
        int num=newsService.getPersonSignNum(name_id);
        int pages = (int) Math.ceil(num/(pageNum*1.0));
        int pageStart = (page - 1) * pageNum;
        int pageEnd=pageStart+pageNum;
        list=newsService.getPersonAllSign(name_id,pageStart,pageNum);
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
}
