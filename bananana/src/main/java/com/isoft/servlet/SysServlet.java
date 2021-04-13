package com.isoft.servlet;

import com.isoft.entity.News;
import com.isoft.entity.Sign;
import com.isoft.entity.Sys;
import com.isoft.service.NewsService;
import com.isoft.service.SysService;
import com.isoft.util.JsonUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/SysServlet")
public class SysServlet extends HttpServlet {
    private SysService sysService;
    private static final String UPLOAD_DIRECTORY = "upload";
    public SysServlet() {
        this.sysService = new SysService();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String args=request.getParameter("method");
        switch (args){
            case "logincheck":
                logincheck(request,response);
                break;
            case "addstudent":
                addstudent(request,response);
                break;
            case "getAllStudent":
                getAllStudent(request,response);
                break;
            case "search":
                search(request,response);
                break;
            case "getStudentDate":
                getStudentDate(request,response);
                break;
            case "updateData":
                updateData(request,response);
                break;
            case "deleteDate":
                deleteDate(request,response);
                break;
            case "getAllschool":
                getAllschool(request,response);
                break;
        }
    }
    protected void getAllschool(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        ArrayList<Sys> list=new ArrayList<>();
        list=sysService.getAllschool();
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(list);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void deleteDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        String id1 = request.getParameter("id") ;
        int id=Integer.parseInt(id1);
        int rs1=sysService.userrubbish(id);
        int rs=sysService.deleteDate(id);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(rs);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void updateData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        String id1 = request.getParameter("id") ;
        int id=Integer.parseInt(id1);
        String name=request.getParameter("name");
        String address=request.getParameter("address");
        String password=request.getParameter("password");
        String sex=request.getParameter("sex");
        String age=request.getParameter("age");
        String sno=request.getParameter("sno");
        String level=request.getParameter("level");
        int rs=sysService.updataDate(name,password,sno,sex,age,address,id,level);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(rs);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void getStudentDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        String id1 = request.getParameter("id") ;
        int id=Integer.parseInt(id1);
        Sys sys = sysService.getStudentDate(id);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(sys);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        int head_id =Integer.parseInt(request.getParameter("head_id")) ;
        String searchtext=request.getParameter("searchtext");
        ArrayList<Sys> list=new ArrayList<>();
        list=sysService.search(head_id,searchtext);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(list);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }

    protected void getAllStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        int head_id =Integer.parseInt(request.getParameter("head_id")) ;
        ArrayList<Sys> list=new ArrayList<>();
        list=sysService.getAllStudent(head_id);
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(list);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
    protected void addstudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        String head = request.getParameter("head") ;
        String name=request.getParameter("name");
        String address=request.getParameter("address");
        String password=request.getParameter("password");
        String sex=request.getParameter("sex");
        String age=request.getParameter("age");
        String sno=request.getParameter("sno");
        String school=request.getParameter("school");
        String schoolmaster=request.getParameter("schoolmaster");
        int schoolmaster_id=Integer.parseInt(request.getParameter("schoolmaster_id"));
        String level=request.getParameter("level");
        String curriculum=request.getParameter("curriculum");
        String class_hours1=request.getParameter("class_hour");
        String class_name=request.getParameter("class_name");
        int class_hour=Integer.parseInt(class_hours1);
        String head_id1=request.getParameter("head_id");
        int head_id=Integer.parseInt(head_id1);
        String parents=request.getParameter("parents");
        String phone=request.getParameter("phone");
        String time=request.getParameter("time");
        int renew=Integer.parseInt(request.getParameter("renew"));
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
            int rs=sysService.addstudent(name,sno,password,sex,age,address,parents,phone,curriculum,class_hour,head,head_id,renew,class_name,school,schoolmaster,schoolmaster_id,level,time);
            JSONArray jsonArray = new JSONArray() ;
            JSONArray json=JSONArray.fromObject(rs);
            String strJson=json.toString();
            Writer out = response.getWriter();
            out.write(strJson);
            out.flush();
        }
    }
    protected void logincheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        PrintWriter out = response.getWriter();
        String sno = request.getParameter("sno") ;
        String password = request.getParameter("password") ;
        Sys sys = sysService.loginCheck(sno , password) ;
        Map<String , Object> map = new HashMap<>() ;
        int errorCode ;
        String errorMsg ;
        if(null == sys) {
            errorCode = 1;
            errorMsg = "login defeat！" ;
        } else {
            errorCode = 0 ;
            errorMsg = "login seccsess！" ;
        }
        JSONArray jsonArray = new JSONArray() ;
        JSONObject jsonObject = new JSONObject() ;
        jsonObject.put("errorCode", errorCode) ;
        jsonObject.put("errorMsg",errorMsg) ;
        jsonObject.put("result",sys) ;
        map.put("errorCode" , errorCode) ;
        map.put("errorMsg" , errorMsg) ;
        map.put("result" , sys) ;
        jsonArray.add(JsonUtil.obj2JsonStr(map)) ;
        out.println(jsonArray);
    }

    /**
     * 日期格式字符串转换成时间戳
     * @param date_str 字符串日期
     * @param date_str 如：yyyy-MM-dd HH:mm:ss
     * @returng
     */
    public static String dateTimeStamp(String date_str){
        try {
            String format="yyyy-MM-dd hh:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime()/1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }




//
//    protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
//        String teacher = request.getParameter("teacher") ;
//        String searchtext=request.getParameter("searchtext");
//        Map<String , Object> map = new HashMap<>() ;
//        ArrayList<Sys> list=new ArrayList<>();
//        list=sysService.search(teacher,searchtext);
////        map.put("text" , list) ;
////        map.put("value" , 1) ;
////        response.getWriter().print(JsonUtil.obj2JsonStr(map));
//        JSONArray jsonArray = new JSONArray() ;
//        JSONArray json=JSONArray.fromObject(list);
//        String strJson=json.toString();
//        Writer out = response.getWriter();
//        out.write(strJson);
//        out.flush();
//    }
//
//    protected void forSign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
//        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
//        String id=request.getParameter("signid");
//        String teacher=request.getParameter("teacher");
//        int teacher_id=Integer.parseInt(request.getParameter("teacher_id"));
//        int name_id=Integer.parseInt(request.getParameter("name_id"));
//        int signid=Integer.parseInt(id);
//        Sys sys=sysService.getStudentDate(name_id);
//        String name=sys.getName();
//        Sign sign =newsService.checkSign(signid,name_id);
//        if(sign!=null){
//            //已替签到
//            int rs=1;
//            JSONArray jsonArray = new JSONArray() ;
//            JSONArray json=JSONArray.fromObject(rs);
//            String strJson=json.toString();
//            Writer out = response.getWriter();
//            out.write(strJson);
//            out.flush();
//        }else{
//            Date date=new Date();
//            int rs1=newsService.forSign(signid,name_id,name,date,teacher_id,teacher);
//            int now_Class_hour=sysService.getStudent_teacher(name_id,teacher_id).getClass_hour();
//            now_Class_hour=now_Class_hour-2;
//            int up=sysService.updata_hour(now_Class_hour,name_id,teacher_id);
//            int rs=0;
//            JSONArray jsonArray = new JSONArray() ;
//            JSONArray json=JSONArray.fromObject(rs);
//            String strJson=json.toString();
//            Writer out = response.getWriter();
//            out.write(strJson);
//            out.flush();
//        }
//
//    }
//
//    protected void getNoSign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
//        String id = request.getParameter("id") ;
//        int teacher_id=Integer.parseInt(request.getParameter("teacher_id"));
//        int signid=Integer.parseInt(id);
//        String sign_class=newsService.getnews(signid).getSign_class();
//        ArrayList<Sys> list=new ArrayList<>();
//        list=newsService.getNoSign(teacher_id,sign_class,signid);
//        JSONArray jsonArray = new JSONArray() ;
//        JSONArray json=JSONArray.fromObject(list);
//        String strJson=json.toString();
//        Writer out = response.getWriter();
//        out.write(strJson);
//        out.flush();
//    }
//
//    protected void getcheckSign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
//        String id = request.getParameter("id") ;
//        int signid=Integer.parseInt(id);
//        ArrayList<Sign> list=new ArrayList<>();
//        list=newsService.getcheckSign(signid);
//        JSONArray jsonArray = new JSONArray() ;
//        JSONArray json=JSONArray.fromObject(list);
//        String strJson=json.toString();
//        Writer out = response.getWriter();
//        out.write(strJson);
//        out.flush();
//    }
//    protected void toSign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
//        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
//        String id=request.getParameter("signid");
//        String name=request.getParameter("name");
//        String teacher=request.getParameter("teacher");
//        int signid=Integer.parseInt(id);
//        int name_id=Integer.parseInt(request.getParameter("name_id"));
//        int teacher_id=Integer.parseInt(request.getParameter("teacher_id"));
//        Sign sign =newsService.checkSign(signid,name_id);
//        if(sign!=null){
//            //已签到
//            int rs=1;
//            JSONArray jsonArray = new JSONArray() ;
//            JSONArray json=JSONArray.fromObject(rs);
//            String strJson=json.toString();
//            Writer out = response.getWriter();
//            out.write(strJson);
//            out.flush();
//            return;
//        }else{
//            Date date=new Date();
//            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Calendar now=Calendar.getInstance();
//            News news=newsService.getnews(signid);
//            Date datesign=news.getTime();
//            if(!datesign.before(date)){
//                //已过签到时间
//                int rs=-1;
//                JSONArray jsonArray = new JSONArray() ;
//                JSONArray json=JSONArray.fromObject(rs);
//                String strJson=json.toString();
//                Writer out = response.getWriter();
//                out.write(strJson);
//                out.flush();
//                return;
//            }else{
//                int rs1=newsService.toSign(signid,name_id,name,date,teacher_id,teacher);
//                int now_Class_hour=sysService.getStudent_teacher(name_id,teacher_id).getClass_hour();
//                now_Class_hour=now_Class_hour-2;
//                int up=sysService.updata_hour(now_Class_hour,name_id,teacher_id);
//                int rs=0;
//                JSONArray jsonArray = new JSONArray() ;
//                JSONArray json=JSONArray.fromObject(rs);
//                String strJson=json.toString();
//                Writer out = response.getWriter();
//                out.write(strJson);
//                out.flush();
//                return;
//            }
//        }
//
//    }
//    protected void putSign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
//        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
//        String user=request.getParameter("user");
//        String text=request.getParameter("text1");
//        String index=request.getParameter("index");
//        String user_id1=request.getParameter("user_id");
//        int user_id=Integer.parseInt(user_id1);
//        String sign_class=request.getParameter("sign_class");
//        Date date=new Date();
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Calendar now=Calendar.getInstance();
//        if(index=="0"){
//            now.add(Calendar.MINUTE,5);
//            String dateStr=sdf.format(now.getTimeInMillis());
//            date = sdf.parse(dateStr);
//        }else if(index=="1"){
//            now.add(Calendar.MINUTE,10);
//            String dateStr=sdf.format(now.getTimeInMillis());
//            date = sdf.parse(dateStr);
//        }else if(index=="2"){
//            now.add(Calendar.MINUTE,10);
//            String dateStr=sdf.format(now.getTimeInMillis());
//            date = sdf.parse(dateStr);
//        }else if(index=="3"){
//            now.add(Calendar.MINUTE,60);
//            String dateStr=sdf.format(now.getTimeInMillis());
//            date = sdf.parse(dateStr);
//        }
//        int rs=newsService.putSign(user,text,sign_class,date,user_id);
//        JSONArray jsonArray = new JSONArray() ;
//        JSONArray json=JSONArray.fromObject(rs);
//        String strJson=json.toString();
//        Writer out = response.getWriter();
//        out.write(strJson);
//        out.flush();
//    }
//    protected void putNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
//        String name=request.getParameter("name");
//        String title=request.getParameter("title");
//        String text=request.getParameter("text");
//        String user_id1=request.getParameter("user_id");
//        int user_id=Integer.parseInt(user_id1);
//        int rs=newsService.putNotice(user_id,name,title,text);
//        JSONArray jsonArray = new JSONArray() ;
//        JSONArray json=JSONArray.fromObject(rs);
//        String strJson=json.toString();
//        Writer out = response.getWriter();
//        out.write(strJson);
//        out.flush();
//    }
//    protected void getAllSign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
//        int name_id = Integer.parseInt(request.getParameter("name_id"));
//        ArrayList<News> list=new ArrayList<>();
//        int page=Integer.parseInt(request.getParameter("page"));
//        int pageNum=Integer.parseInt(request.getParameter("pageNum"));
//        int num=newsService.getSignNum(name_id);
//        int pages = (int) Math.ceil(num/(pageNum*1.0));
//        int pageStart = (page - 1) * pageNum;
//        int pageEnd=pageStart+pageNum;
//        list=newsService.getSign(name_id,pageStart,pageNum);
//        JSONArray jsonArray = new JSONArray() ;
//        JSONArray json=JSONArray.fromObject(list);
//        String strJson=json.toString();
//        Writer out = response.getWriter();
//        out.write(strJson);
//        out.flush();
//    }
//    protected void getStuAllSign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
//        int teacher_id = Integer.parseInt(request.getParameter("teacher_id"));
//        ArrayList<News> list=new ArrayList<>();
//        int page=Integer.parseInt(request.getParameter("page"));
//        int pageNum=Integer.parseInt(request.getParameter("pageNum"));
//        String sign_class=request.getParameter("sign_class");
//        int num=newsService.getStuSignNum(teacher_id,sign_class);
//        int pages = (int) Math.ceil(num/(pageNum*1.0));
//        int pageStart = (page - 1) * pageNum;
//        int pageEnd=pageStart+pageNum;
//        list=newsService.getStuSign(teacher_id,sign_class,pageStart,pageNum);
//        JSONArray jsonArray = new JSONArray() ;
//        JSONArray json=JSONArray.fromObject(list);
//        String strJson=json.toString();
//        Writer out = response.getWriter();
//        out.write(strJson);
//        out.flush();
//    }
//    protected void getPersonAllSign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
//        int name_id = Integer.parseInt(request.getParameter("name_id"));
//        ArrayList<Sign> list=new ArrayList<>();
//        int page=Integer.parseInt(request.getParameter("page"));
//        int pageNum=Integer.parseInt(request.getParameter("pageNum"));
//        int num=newsService.getPersonSignNum(name_id);
//        int pages = (int) Math.ceil(num/(pageNum*1.0));
//        int pageStart = (page - 1) * pageNum;
//        int pageEnd=pageStart+pageNum;
//        list=newsService.getPersonAllSign(name_id,pageStart,pageNum);
//        JSONArray jsonArray = new JSONArray() ;
//        JSONArray json=JSONArray.fromObject(list);
//        String strJson=json.toString();
//        Writer out = response.getWriter();
//        out.write(strJson);
//        out.flush();
//    }
//    protected void getAlldynamic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
//        int user_id = Integer.parseInt(request.getParameter("user_id"));
//        ArrayList<News> list=new ArrayList<>();
//        int page=Integer.parseInt(request.getParameter("page"));
//        int pageNum=Integer.parseInt(request.getParameter("pageNum"));
//        int num=newsService.getNewsNum(user_id);
//        int pages = (int) Math.ceil(num/(pageNum*1.0));
//        int pageStart = (page - 1) * pageNum;
//        int pageEnd=pageStart+pageNum;
//        list=newsService.getsome(user_id,pageStart,pageNum);
//        JSONArray jsonArray = new JSONArray() ;
//        JSONArray json=JSONArray.fromObject(list);
//        String strJson=json.toString();
//        Writer out = response.getWriter();
//        out.write(strJson);
//        out.flush();
//    }
//    protected void logincheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
//        PrintWriter out = response.getWriter();
//        String phone = request.getParameter("phone") ;
//        String password = request.getParameter("password") ;
//        Sys sys = sysService.loginCheck(phone , password) ;
//        Map<String , Object> map = new HashMap<>() ;
//        int errorCode ;
//        String errorMsg ;
//        if(null == sys) {
//            errorCode = 1;
//            errorMsg = "login defeat！" ;
//        } else {
//            errorCode = 0 ;
//            errorMsg = "login seccsess！" ;
//        }
//        JSONArray jsonArray = new JSONArray() ;
//        JSONObject jsonObject = new JSONObject() ;
//        jsonObject.put("errorCode", errorCode) ;
//        jsonObject.put("errorMsg",errorMsg) ;
//        jsonObject.put("result",sys) ;
//        map.put("errorCode" , errorCode) ;
//        map.put("errorMsg" , errorMsg) ;
//        map.put("result" , sys) ;
//        jsonArray.add(JsonUtil.obj2JsonStr(map)) ;
//        out.println(jsonArray);
//    }
//    protected void getStudentDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
//        String id1 = request.getParameter("id") ;
//        int id=Integer.parseInt(id1);
//        Sys sys = sysService.getStudentDate(id);
//        JSONArray jsonArray = new JSONArray() ;
//        JSONArray json=JSONArray.fromObject(sys);
//        String strJson=json.toString();
//        Writer out = response.getWriter();
//        out.write(strJson);
//        out.flush();
//    }
//    protected void getAllStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
//        int teacher_id =Integer.parseInt(request.getParameter("teacher_id")) ;
//        ArrayList<Sys> list=new ArrayList<>();
//        list=sysService.getAllStudent(teacher_id);
//        JSONArray jsonArray = new JSONArray() ;
//        JSONArray json=JSONArray.fromObject(list);
//        String strJson=json.toString();
//        Writer out = response.getWriter();
//        out.write(strJson);
//        out.flush();
//    }
//    protected void deleteDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
//        String id1 = request.getParameter("id") ;
//        int id=Integer.parseInt(id1);
//        int rs=sysService.deleteDate(id);
//        JSONArray jsonArray = new JSONArray() ;
//        JSONArray json=JSONArray.fromObject(rs);
//        String strJson=json.toString();
//        Writer out = response.getWriter();
//        out.write(strJson);
//        out.flush();
//    }
//    protected void updateData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
//        String id1 = request.getParameter("id") ;
//        int id=Integer.parseInt(id1);
//        String name=request.getParameter("name");
//        String address=request.getParameter("address");
//        String password=request.getParameter("password");
//        String sex=request.getParameter("sex");
//        int age=Integer.parseInt(request.getParameter("age"));
//        String phone=request.getParameter("phone");
//        int rs=sysService.updataDate(name,password,phone,sex,age,address,id);
//        JSONArray jsonArray = new JSONArray() ;
//        JSONArray json=JSONArray.fromObject(rs);
//        String strJson=json.toString();
//        Writer out = response.getWriter();
//        out.write(strJson);
//        out.flush();
//    }
//    protected void subStuDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
//        String teacher = request.getParameter("teacher") ;
//        String name=request.getParameter("name");
//        String address=request.getParameter("address");
//        String password=request.getParameter("password");
//        String sex=request.getParameter("sex");
//        int age=Integer.parseInt(request.getParameter("age"));
//        String phone=request.getParameter("phone");
//        String curriculum=request.getParameter("curriculum");
//        String class_hours1=request.getParameter("class_hour");
//        int class_hour=Integer.parseInt(class_hours1);
//        String teacher_id1=request.getParameter("teacher_id");
//        int teacher_id=Integer.parseInt(teacher_id1);
//        String parents=request.getParameter("parents");
//        String parents_phone=request.getParameter("parents_phone");
//        int renew=Integer.parseInt(request.getParameter("renew"));
//        int rs=sysService.subStuDate(name,phone,password,sex,age,address,parents,parents_phone,curriculum,class_hour,teacher,teacher_id,renew);
//        JSONArray jsonArray = new JSONArray() ;
//        JSONArray json=JSONArray.fromObject(rs);
//        String strJson=json.toString();
//        Writer out = response.getWriter();
//        out.write(strJson);
//        out.flush();
//    }
//    protected void deleteSign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
//        String id1 = request.getParameter("id") ;
//        int id=Integer.parseInt(id1);
//        int rs2=newsService.deleteSigned(id);
//        int rs=newsService.deleteSign(id);
//        JSONArray jsonArray = new JSONArray() ;
//        JSONArray json=JSONArray.fromObject(rs);
//        String strJson=json.toString();
//        Writer out = response.getWriter();
//        out.write(strJson);
//        out.flush();
//    }
}