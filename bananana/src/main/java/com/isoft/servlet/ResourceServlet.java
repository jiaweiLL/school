package com.isoft.servlet;

import com.isoft.entity.Resource;
import com.isoft.service.ResourceService;
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

@WebServlet("/ResourceServlet")
public class ResourceServlet extends HttpServlet {
    private ResourceService resourceService;
    private static final String UPLOAD_DIRECTORY = "upload";
    public  ResourceServlet() {
        this.resourceService = new  ResourceService();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String args=request.getParameter("method");
        switch (args){
            case "updateRollPicture":
                updateRollPicture(request,response);
                break;
            case "getRollPicture":
                getRollPicture(request,response);
                break;
            case "addNoticeVideo":
                addNoticeVideo(request,response);
                break;
            case "getNoticeVideoSome":
                getNoticeVideoSome(request,response);
                break;
            case "addNotice":
                addNotice(request,response);
                break;
            case "getNoticeSome":
                getNoticeSome(request,response);
                break;
            case "getNoticeData":
                getNoticeData(request,response);
                break;
            case "addDance":
                addDance(request,response);
                break;
            case "getDance":
                getDance(request,response);
                break;
            case "getDanceData":
                getDanceData(request,response);
                break;
        }
    }
    protected void getDance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        ArrayList<Resource> list=new ArrayList<>();
        int page=Integer.parseInt(request.getParameter("page"));
        int pageNum=Integer.parseInt(request.getParameter("pageNum"));
        String coursetype=request.getParameter("coursetype");
        int num=resourceService.getDanceNum(coursetype);
        int pages = (int) Math.ceil(num/(pageNum*1.0));
        int pageStart = (page - 1) * pageNum;
        int pageEnd=pageStart+pageNum;
        list=resourceService.getDanceSome(pageStart,pageEnd,coursetype);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(list);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void addDance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为j
        String picSrc =request.getParameter("picSrc").trim();
        int putid=Integer.parseInt(request.getParameter("putid"));
        String putname=request.getParameter("putname");
        long time1 = System.currentTimeMillis();
        String time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time1);
        String videoSrc=request.getParameter("videoSrc");
        String title=request.getParameter("title");
        String content=request.getParameter("content");
        String coursetype=request.getParameter("coursetype");
        String url[]=picSrc.split(" ");
        int rs=resourceService.addDance(videoSrc,title,url[0],content,coursetype,putid,putname,time);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(rs);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void getNoticeData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        int id=Integer.parseInt(request.getParameter("id"));
        Resource resource=resourceService.getNoticeData(id);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(resource);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void getDanceData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        int id=Integer.parseInt(request.getParameter("id"));
        Resource resource=resourceService.getDanceData(id);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(resource);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void getNoticeSome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        ArrayList<Resource> list=new ArrayList<>();
        int page=Integer.parseInt(request.getParameter("page"));
        int pageNum=Integer.parseInt(request.getParameter("pageNum"));
        int num=resourceService.getNoticeNum();
        int pages = (int) Math.ceil(num/(pageNum*1.0));
        int pageStart = (page - 1) * pageNum;
        int pageEnd=pageStart+pageNum;
        list=resourceService.getNoticeSome(pageStart,pageEnd);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(list);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void getNoticeVideoSome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        ArrayList<Resource> list=new ArrayList<>();
        int page=Integer.parseInt(request.getParameter("page"));
        int pageNum=Integer.parseInt(request.getParameter("pageNum"));
        int num=resourceService.getNoticeVideoNum();
        int pages = (int) Math.ceil(num/(pageNum*1.0));
        int pageStart = (page - 1) * pageNum;
        int pageEnd=pageStart+pageNum;
        list=resourceService.getNoticeVideoSome(pageStart,pageEnd);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(list);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void addNoticeVideo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为j
        String picSrc =request.getParameter("picSrc").trim();
        int putid=Integer.parseInt(request.getParameter("putid"));
        String putname=request.getParameter("putname");
        long time1 = System.currentTimeMillis();
        String time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time1);
        String videoSrc=request.getParameter("videoSrc");
        String title=request.getParameter("title");
        String content=request.getParameter("content");
        String url[]=picSrc.split(" ");
        int rs=resourceService.addNoticeVideo(videoSrc,title,url[0],content,putid,putname,time);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(rs);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void addNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为j
        String picSrc =request.getParameter("picSrc").trim();
        int putid=Integer.parseInt(request.getParameter("putid"));
        String putname=request.getParameter("putname");
        long time1 = System.currentTimeMillis();
        String time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time1);
        String title=request.getParameter("title");
        String content=request.getParameter("content");
        String url[]=picSrc.split(" ");
        int rs=resourceService.addNotice(url[0],title,content,putid,putname,time);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(rs);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void getRollPicture(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为j
        ArrayList<Resource> resources=new ArrayList<>();
        resources=resourceService.getRollPicture();
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(resources);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void updateRollPicture(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为j
        String strUrl =request.getParameter("strUrl").trim();
        int putid=Integer.parseInt(request.getParameter("putid"));
        String putname=request.getParameter("putname");

        long time1 = System.currentTimeMillis();
        String time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time1);
        String url[]=strUrl.split(" ");
        for(int i=0;i<url.length;i++){
            int j=i+1;
            String title=""+j;
            System.out.println(url[i]);
            int rs=resourceService.updateRollPicture(url[i],putid,putname,time,title);
        }
        int a=1;
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(a);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
}
