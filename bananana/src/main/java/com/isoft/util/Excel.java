package com.isoft.util;

import com.isoft.entity.Apply;
import com.isoft.entity.Entry;
import com.isoft.entity.Storehouse;
import com.isoft.entity.Sys;
import com.sun.mail.util.MailSSLSocketFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Excel {
    /**
     * 发送带附件的邮件
     *
     * @param receive
     *            收件人
     * @param subject
     *            邮件主题
     * @param msg
     *            邮件内容
     * @param filename
     *            附件地址
     * @return
     * @throws GeneralSecurityException
     */
    public static boolean sendMail(String receive, String subject, String msg, String filename)
            throws GeneralSecurityException {
        if (StringUtils.isEmpty(receive)) {
            return false;
        }

        // 发件人电子邮箱
        final String from = "15339900206@sina.cn";
        // 发件人电子邮箱密码
        final String pass = "a6607220580e1c87";

        // 指定发送邮件的主机为 smtp.qq.com
        String host = "smtp.sina.cn"; // 邮件服务器

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() { // qq邮箱服务器账户、第三方登录授权码
                return new PasswordAuthentication(from, pass); // 发件人邮件用户名、密码
            }
        });

        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receive));

            // Set Subject: 主题文字
            message.setSubject(subject);

            // 创建消息部分
            BodyPart messageBodyPart = new MimeBodyPart();

            // 消息
            messageBodyPart.setText(msg);

            // 创建多重消息
            Multipart multipart = new MimeMultipart();

            // 设置文本消息部分
            multipart.addBodyPart(messageBodyPart);

            // 附件部分
            messageBodyPart = new MimeBodyPart();
            // 设置要发送附件的文件路径
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));

            // mes
            // sageBodyPart.setFileName(filename);
            // 处理附件名称中文（附带文件路径）乱码问题
            messageBodyPart.setFileName(MimeUtility.encodeText(filename));
            multipart.addBodyPart(messageBodyPart);

            // 发送完整消息
            message.setContent(multipart);

            // 发送消息
            Transport.send(message);
            // System.out.println("Sent message successfully....");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean putadvice(String receive, String subject, String msg) throws GeneralSecurityException {
        if (StringUtils.isEmpty(receive)) {
            return false;
        }

        // 发件人电子邮箱
        final String from = "15339900206@sina.cn";
        // 发件人电子邮箱密码
        final String pass = "a6607220580e1c87";

        // 指定发送邮件的主机为 smtp.qq.com
        String host = "smtp.sina.cn"; // 邮件服务器

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() { // qq邮箱服务器账户、第三方登录授权码
                return new PasswordAuthentication(from, pass); // 发件人邮件用户名、密码
            }
        });
        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receive));

            // Set Subject: 主题文字
            message.setSubject(subject);

            // 创建消息部分
            BodyPart messageBodyPart = new MimeBodyPart();

            // 消息
            messageBodyPart.setText(msg);

            // 创建多重消息
            Multipart multipart = new MimeMultipart();

            // 设置文本消息部分
            multipart.addBodyPart(messageBodyPart);

            // 发送完整消息
            message.setContent(multipart);

            // 发送消息
            Transport.send(message);
            // System.out.println("Sent message successfully....");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static String studentExcel(ArrayList<Sys> student) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd hhmmss");
        Workbook wb = new XSSFWorkbook();
        //标题行抽出字段
        String[] title = {"序号","账号","密码" ,"姓名", "性别","住址", "手机号","等级","校区", "总缴费","续费","入学时间"};
        //设置sheet名称，并创建新的sheet对象
        String sheetName = "学生";
        Sheet stuSheet = wb.createSheet(sheetName);
        //获取表头行
        Row titleRow = stuSheet.createRow(0);
        //创建单元格，设置style居中，字体，单元格大小等
        CellStyle style = wb.createCellStyle();
        Cell cell = null;
        //把已经写好的标题行写入excel文件中
        for (int i = 0; i < title.length; i++) {
            cell = titleRow.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }
        //把从数据库中取得的数据一一写入excel文件中
        Row row = null;
        for (int i = 0; i < student.size(); i++) {
            //创建list.size()行数据
            row = stuSheet.createRow(i + 1);
            //把值一一写进单元格里
            //设置第一列为自动递增的序号
            row.createCell(2).setCellValue(student.get(i).getSno());
            row.createCell(3).setCellValue(student.get(i).getPassword());
            row.createCell(4).setCellValue(student.get(i).getName());
            row.createCell(5).setCellValue(student.get(i).getSex());
            //把时间转换为指定格式的字符串再写入excel文件中
            row.createCell(6).setCellValue(student.get(i).getAddress());
            row.createCell(7).setCellValue(student.get(i).getPhone());
            row.createCell(8).setCellValue(student.get(i).getLevel());
            row.createCell(9).setCellValue(student.get(i).getSchool());
            row.createCell(10).setCellValue(student.get(i).getTotal_pay());
            row.createCell(11).setCellValue(student.get(i).getRenew());
            row.createCell(12).setCellValue(student.get(i).getTime());

        }
        //设置单元格宽度自适应，在此基础上把宽度调至1.5倍
        for (int i = 0; i < title.length; i++) {
            stuSheet.autoSizeColumn(i, true);
            stuSheet.setColumnWidth(i, stuSheet.getColumnWidth(i) * 15 / 10);
        }
        //获取配置文件中保存对应excel文件的路径，本地也可以直接写成F：excel/stuInfoExcel路径
        String folderPath = "exlce";
        //创建上传文件目录
        File folder = new File(folderPath);
        //如果文件夹不存在创建对应的文件夹
        if (!folder.exists()) {
            folder.mkdirs();
        }
        //设置文件名
        String fileName = sdf1.format(new Date()) + sheetName + ".xlsx";
        String savePath = folderPath + File.separator + fileName;
        // System.out.println(savePath);
        OutputStream fileOut = new FileOutputStream(savePath);
        wb.write(fileOut);
        fileOut.close();
        //返回文件保存全路径
        return savePath;
    }
    public static String storeExcel(ArrayList<Storehouse> storehouses) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd hhmmss");
        Workbook wb = new XSSFWorkbook();
        //标题行抽出字段
        String[] title = {"序号","品名","品牌" ,"规格", "类别","采购价格", "指导价格","计量单位","库存数量", "本月领用","下月申购","所属","时间"};
        //设置sheet名称，并创建新的sheet对象
        String sheetName = "申请";
        Sheet stuSheet = wb.createSheet(sheetName);
        //获取表头行
        Row titleRow = stuSheet.createRow(0);
        //创建单元格，设置style居中，字体，单元格大小等
        CellStyle style = wb.createCellStyle();
        Cell cell = null;
        //把已经写好的标题行写入excel文件中
        for (int i = 0; i < title.length; i++) {
            cell = titleRow.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }
        //把从数据库中取得的数据一一写入excel文件中
        Row row = null;
        for (int i = 0; i < storehouses.size(); i++) {
            //创建list.size()行数据
            row = stuSheet.createRow(i + 1);
            //把值一一写进单元格里
            //设置第一列为自动递增的序号
            row.createCell(0).setCellValue(i + 1);
            row.createCell(1).setCellValue(storehouses.get(i).getSproduct_Name());
            row.createCell(2).setCellValue(storehouses.get(i).getSbrand());
            row.createCell(3).setCellValue(storehouses.get(i).getSpecifications());
            row.createCell(4).setCellValue(storehouses.get(i).getScategory());
            //把时间转换为指定格式的字符串再写入excel文件中
            row.createCell(5).setCellValue(storehouses.get(i).getSbuy_price());
            row.createCell(6).setCellValue(storehouses.get(i).getSguide_price());
            row.createCell(7).setCellValue(storehouses.get(i).getSunit());
            row.createCell(8).setCellValue(storehouses.get(i).getSnumber());
            row.createCell(9).setCellValue(storehouses.get(i).getScollected_month());
            row.createCell(10).setCellValue(storehouses.get(i).getSnext_month());
            row.createCell(11).setCellValue(storehouses.get(i).getSchool());
            row.createCell(12).setCellValue(storehouses.get(i).getTime());


        }
        //设置单元格宽度自适应，在此基础上把宽度调至1.5倍
        for (int i = 0; i < title.length; i++) {
            stuSheet.autoSizeColumn(i, true);
            stuSheet.setColumnWidth(i, stuSheet.getColumnWidth(i) * 15 / 10);
        }
        //获取配置文件中保存对应excel文件的路径，本地也可以直接写成F：excel/stuInfoExcel路径
        String folderPath = "exlce";
        //创建上传文件目录
        File folder = new File(folderPath);
        //如果文件夹不存在创建对应的文件夹
        if (!folder.exists()) {
            folder.mkdirs();
        }
        //设置文件名
        String fileName = sdf1.format(new Date()) + sheetName + ".xlsx";
        String savePath = folderPath + File.separator + fileName;
        // System.out.println(savePath);
        OutputStream fileOut = new FileOutputStream(savePath);
        wb.write(fileOut);
        fileOut.close();
        //返回文件保存全路径
        return savePath;
    }
    public static String entryExcel(ArrayList<Entry> entry) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd hhmmss");
        Workbook wb = new XSSFWorkbook();
        //标题行抽出字段
        String[] title = {"序号","汇款帐户","入账金额" ,"入账事项", "保管员","负责人", "所属部门","日期"};
        //设置sheet名称，并创建新的sheet对象
        String sheetName = "入账";
        Sheet stuSheet = wb.createSheet(sheetName);
        //获取表头行
        Row titleRow = stuSheet.createRow(0);
        //创建单元格，设置style居中，字体，单元格大小等
        CellStyle style = wb.createCellStyle();
        Cell cell = null;
        //把已经写好的标题行写入excel文件中
        for (int i = 0; i < title.length; i++) {
            cell = titleRow.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }
        //把从数据库中取得的数据一一写入excel文件中
        Row row = null;
        for (int i = 0; i < entry.size(); i++) {
            //创建list.size()行数据
            row = stuSheet.createRow(i + 1);
            //把值一一写进单元格里
            //设置第一列为自动递增的序号
            row.createCell(0).setCellValue(i + 1);
            row.createCell(1).setCellValue(entry.get(i).getEremit_account());
            row.createCell(2).setCellValue(entry.get(i).getEremit_money());
            row.createCell(3).setCellValue(entry.get(i).getEremit_note());
            row.createCell(4).setCellValue(entry.get(i).getEstoreman());
            //把时间转换为指定格式的字符串再写入excel文件中
            row.createCell(5).setCellValue(entry.get(i).getEleadman());
            row.createCell(6).setCellValue(entry.get(i).getEdepartment());
            row.createCell(7).setCellValue(entry.get(i).getEtime());


        }
        //设置单元格宽度自适应，在此基础上把宽度调至1.5倍
        for (int i = 0; i < title.length; i++) {
            stuSheet.autoSizeColumn(i, true);
            stuSheet.setColumnWidth(i, stuSheet.getColumnWidth(i) * 15 / 10);
        }
        //获取配置文件中保存对应excel文件的路径，本地也可以直接写成F：excel/stuInfoExcel路径
        String folderPath = "exlce";
        //创建上传文件目录
        File folder = new File(folderPath);
        //如果文件夹不存在创建对应的文件夹
        if (!folder.exists()) {
            folder.mkdirs();
        }
        //设置文件名
        String fileName = sdf1.format(new Date()) + sheetName + ".xlsx";
        String savePath = folderPath + File.separator + fileName;
        // System.out.println(savePath);
        OutputStream fileOut = new FileOutputStream(savePath);
        wb.write(fileOut);
        fileOut.close();
        //返回文件保存全路径
        return savePath;
    }
    public static String applyExcel(ArrayList<Apply> apply) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd hhmmss");
        Workbook wb = new XSSFWorkbook();
        //标题行抽出字段
        String[] title = {"序号","品名","品牌" ,"规格", "类别","计量单位", "数量","单价","金额", "领用部门","领用人","用途","入账时间"};
        //设置sheet名称，并创建新的sheet对象
        String sheetName = "申请";
        Sheet stuSheet = wb.createSheet(sheetName);
        //获取表头行
        Row titleRow = stuSheet.createRow(0);
        //创建单元格，设置style居中，字体，单元格大小等
        CellStyle style = wb.createCellStyle();
        Cell cell = null;
        //把已经写好的标题行写入excel文件中
        for (int i = 0; i < title.length; i++) {
            cell = titleRow.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }
        //把从数据库中取得的数据一一写入excel文件中
        Row row = null;
        for (int i = 0; i < apply.size(); i++) {
            //创建list.size()行数据
            row = stuSheet.createRow(i + 1);
            //把值一一写进单元格里
            //设置第一列为自动递增的序号
            row.createCell(0).setCellValue(i + 1);
            row.createCell(1).setCellValue(apply.get(i).getAproduct_name());
            row.createCell(2).setCellValue(apply.get(i).getAbrand());
            row.createCell(3).setCellValue(apply.get(i).getAspecifications());
            row.createCell(4).setCellValue(apply.get(i).getAcategory());
            //把时间转换为指定格式的字符串再写入excel文件中
            row.createCell(5).setCellValue(apply.get(i).getAunit());
            row.createCell(6).setCellValue(apply.get(i).getAnumber());
            row.createCell(7).setCellValue(apply.get(i).getAbuy_price());
            row.createCell(8).setCellValue(apply.get(i).getAprice());
            row.createCell(9).setCellValue(apply.get(i).getAdepartment());
            row.createCell(10).setCellValue(apply.get(i).getAname());
            row.createCell(11).setCellValue(apply.get(i).getAuse());
            row.createCell(12).setCellValue(apply.get(i).getAtime());


        }
        //设置单元格宽度自适应，在此基础上把宽度调至1.5倍
        for (int i = 0; i < title.length; i++) {
            stuSheet.autoSizeColumn(i, true);
            stuSheet.setColumnWidth(i, stuSheet.getColumnWidth(i) * 15 / 10);
        }
        //获取配置文件中保存对应excel文件的路径，本地也可以直接写成F：excel/stuInfoExcel路径
        String folderPath = "exlce";
        //创建上传文件目录
        File folder = new File(folderPath);
        //如果文件夹不存在创建对应的文件夹
        if (!folder.exists()) {
            folder.mkdirs();
        }
        //设置文件名
        String fileName = sdf1.format(new Date()) + sheetName + ".xlsx";
        String savePath = folderPath + File.separator + fileName;
        // System.out.println(savePath);
        OutputStream fileOut = new FileOutputStream(savePath);
        wb.write(fileOut);
        fileOut.close();
        //返回文件保存全路径
        return savePath;
    }
    public boolean deleteExcel(String fileDir){
        boolean flag = false;
        File file = new File(fileDir);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                file.delete();
                flag = true;
            }
        }
        return flag;
    }
}