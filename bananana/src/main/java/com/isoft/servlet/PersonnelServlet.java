package com.isoft.servlet;

import com.isoft.entity.Sys;
import com.isoft.service.PersonnelSerivice;
import com.isoft.service.SysService;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet("/PersonnelServlet")
public class PersonnelServlet extends HttpServlet {
    private PersonnelSerivice personnelSerivice;
    private SysService sysService;
    private static final String UPLOAD_DIRECTORY = "upload";
    public PersonnelServlet() {
        this.personnelSerivice = new PersonnelSerivice();
        this.sysService = new SysService();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String args=request.getParameter("method");
        switch (args){
            case "addPeople":
                addPeople(request,response);
                break;
        }
    }
    protected void addPeople(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        String name=request.getParameter("name");
        String address=request.getParameter("address");
        String password=request.getParameter("password");
        String sex=request.getParameter("sex");
        String age=request.getParameter("age");
        String sno=request.getParameter("sno");
        String phone=request.getParameter("phone");
        String time=request.getParameter("time");
        String school=request.getParameter("school");
        String power=request.getParameter("power").substring(2);
        String job=request.getParameter("power").substring(0,2);
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
            int rs=personnelSerivice.addPeople(name,sno,password,sex,age,address,phone,school,time,power,job);
            JSONArray jsonArray = new JSONArray() ;
            JSONArray json=JSONArray.fromObject(rs);
            String strJson=json.toString();
            Writer out = response.getWriter();
            out.write(strJson);
            out.flush();
        }

    }
}
