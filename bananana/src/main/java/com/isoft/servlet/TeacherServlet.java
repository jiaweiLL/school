package com.isoft.servlet;

import com.isoft.entity.Sys;
import com.isoft.service.SysService;
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

@WebServlet("/TeacherServlet")
public class TeacherServlet extends HttpServlet {
    private SysService sysService;
    private Excel excel;
    private static final String UPLOAD_DIRECTORY = "upload";
    public TeacherServlet() {
        this.sysService = new SysService();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String args=request.getParameter("method");
        switch (args){
            case "putExcle":
                try {
                    putExcle(request,response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "putAdvise":
                try {
                    putAdvise(request,response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
    protected void putExcle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        int head_id =Integer.parseInt(request.getParameter("id")) ;
        ArrayList<Sys> list=new ArrayList<>();
        list=sysService.getAllStudent(head_id);
        String receive=request.getParameter("receive");
        String subject =request.getParameter("subject");
        String msg ="学生信息表";
        String filename=excel.studentExcel(list);
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
    protected void putAdvise(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        String msg=request.getParameter("msg");
        String subject =request.getParameter("subject");
        String receive="2804247857@qq.com";
        boolean rs=false;
        try {
            rs=excel.putadvice(receive, subject, msg);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = new JSONArray() ;
        JSONArray json=JSONArray.fromObject(rs);
        String strJson=json.toString();
        Writer out = response.getWriter();
        out.write(strJson);
        out.flush();
    }
}
