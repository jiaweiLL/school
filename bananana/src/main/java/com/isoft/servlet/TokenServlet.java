package com.isoft.servlet;

import com.qiniu.util.Auth;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/TokenServlet")
public class TokenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accessKey = "lxjhDkMaxfT_Z7yVE1T6YnY4yhBNXk9i8XEimHMB";
        String secretKey = "DqsHWY8mLEQfctw0iPkLTpH0apP4bo4vojYp9bkk";
        // 要上传的空间名--
        String bucket = "flyingdance";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
//		String key = "zcm******/"+fileName;
        String  key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket, key);
        response.setContentType("text/html;charset=UTF-8");
//		PrintWriter out = response.getWriter();
//		out.write(upToken);
        JSONObject obj = new JSONObject() ; // 定义一个描述json的数据
        try
        {
            obj.put("uptoken",upToken);

        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        response.getWriter().write(obj.toString());
    }
}
