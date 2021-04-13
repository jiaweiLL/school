package com.isoft.entity;

public class Resource {
    private int id;
    private String url;//D地址
    private String title;//标题
    private String text;//文本
    private String content;//文本
    private String filetype;//文件类型
    private String coursetype;//课程类型
    private int putid;//上传id
    private String putname;//上传名字
    private String time;//时间
    @Override
    public String toString() {
        return "ResourceDao{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", content='" + content + '\'' +
                ", filetype='" + filetype + '\'' +
                ", coursetype='" + coursetype + '\'' +
                ", putid=" + putid +
                ", putname='" + putname + '\'' +
                ", time='" + time + '\'' +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getCoursetype() {
        return coursetype;
    }

    public void setCoursetype(String coursetype) {
        this.coursetype = coursetype;
    }

    public int getPutid() {
        return putid;
    }

    public void setPutid(int putid) {
        this.putid = putid;
    }

    public String getPutname() {
        return putname;
    }

    public void setPutname(String putname) {
        this.putname = putname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public Resource(int id, String url, String title, String text, String content, String filetype, String coursetype, int putid, String putname, String time) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.text = text;
        this.content = content;
        this.filetype = filetype;
        this.coursetype = coursetype;
        this.putid = putid;
        this.putname = putname;
        this.time = time;
    }


}
