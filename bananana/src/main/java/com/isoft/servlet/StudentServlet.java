package com.isoft.servlet;

import com.isoft.entity.News;
import com.isoft.entity.Sign;
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
import java.util.Date;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
    private NewsService newsService;
    private SysService sysService;
    private static final String UPLOAD_DIRECTORY = "upload";
    public StudentServlet() {
        this.newsService = new NewsService();
        this.sysService = new SysService();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String args=request.getParameter("method");
        switch (args){
            case "getStuAllSign":
                getStuAllSign(request,response);
                break;
            case "toSign":
                toSign(request,response);
                break;
        }
    }
    protected void toSign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        String id=request.getParameter("newsid");
        String name=request.getParameter("name");
        String head_name=request.getParameter("head_name");
        int newsid=Integer.parseInt(id);
        int name_id=Integer.parseInt(request.getParameter("name_id"));
        int head_id=Integer.parseInt(request.getParameter("head_id"));
        String class_name=request.getParameter("class_name");
        String school=request.getParameter("school");
        Sign sign =newsService.checkSign(newsid,name_id);
        if(sign!=null){
            //已签到
            int rs=2;
            JSONArray jsonArray = new JSONArray() ;
            JSONArray json=JSONArray.fromObject(rs);
            String strJson=json.toString();
            Writer out = response.getWriter();
            out.write(strJson);
            out.flush();
            return;
        }else{
            News news=newsService.getnews(newsid);
            String starttime=news.getStarttime();
            String endtime=news.getEndtime();
            int rs=checksigntime(starttime,endtime);
            System.out.println(rs);
            if(rs==0){
                //不到签到时间
                JSONArray jsonArray = new JSONArray() ;
                JSONArray json=JSONArray.fromObject(rs);
                String strJson=json.toString();
                Writer out = response.getWriter();
                out.write(strJson);
                out.flush();
                return;
            }else if(rs==1){
                //可以签到
                long time1 = System.currentTimeMillis();
                String time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time1);
                int rs1=newsService.toSign(newsid,name_id,name,time,head_id,head_name,school,class_name);
                int now_Class_hour=sysService.getStudent_teacher(name_id,head_id).getClass_hour();
                now_Class_hour=now_Class_hour-2;
                int up=sysService.updata_hour(now_Class_hour,name_id,head_id);
                JSONArray jsonArray = new JSONArray() ;
                JSONArray json=JSONArray.fromObject(rs);
                String strJson=json.toString();
                Writer out = response.getWriter();
                out.write(strJson);
                out.flush();
                return;
            }else{
                //已过签到时间-1
                JSONArray jsonArray = new JSONArray() ;
                JSONArray json=JSONArray.fromObject(rs);
                String strJson=json.toString();
                Writer out = response.getWriter();
                out.write(strJson);
                out.flush();
                return;
            }
        }

    }
    protected void getStuAllSign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        int head_id = Integer.parseInt(request.getParameter("head_id"));
        ArrayList<News> list=new ArrayList<>();
        int page=Integer.parseInt(request.getParameter("page"));
        int pageNum=Integer.parseInt(request.getParameter("pageNum"));
        String sign_class=request.getParameter("sign_class");
        int num=newsService.getStuSignNum(head_id,sign_class);
        int pages = (int) Math.ceil(num/(pageNum*1.0));
        int pageStart = (page - 1) * pageNum;
        int pageEnd=pageStart+pageNum;
        list=newsService.getStuSign(head_id,sign_class,pageStart,pageNum);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(list);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    public static int checksigntime(String start,String end){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String now1=sdf.format(date).replaceAll("[-\\s:]", "");
        int before=start.compareTo(now1);//-1为可以签到
        int after=end.compareTo(now1);//1为可以签到
        if(after>=1&&before>=1){
            return 0;//不到签到时间
        }else if(before<=-1&&after>=1){
            return 1;//可以签到时间
        }else{
            return -1;//已过签到
        }
    }
}
